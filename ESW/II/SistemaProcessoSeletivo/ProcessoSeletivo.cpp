#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <algorithm>
#include <ctime>
#include <cstdlib>
using namespace std;

// Documento
class Documento {
public:
    string tipo;
    string nomeArquivo;
    float pontuacao; // Pontuação atribuída pela banca
    Documento(string t, string n, float p = 0.0f) : tipo(t), nomeArquivo(n), pontuacao(p) {}
};

// Candidato
class Candidato {
public:
    int id;
    string nome;
    string cpf;
    string email;
    string senha;
    vector<Documento> documentos;
    float pontuacaoTotal = 0;
    string status = "Inscrito"; // Etapas: Inscrito > Em análise > Aprovado/Reprovado

    Candidato(int i, string n, string c, string e, string s)
        : id(i), nome(n), cpf(c), email(e), senha(s) {}

    void adicionarDocumento(Documento doc) {
        documentos.push_back(doc);
    }
};

// Vaga
class Vaga {
public:
    int id;
    string disciplina;
    string requisitos;
    string status = "Aberta"; // Aberta, Em análise, Encerrada
    vector<int> candidatosInscritos; // IDs dos candidatos
    Vaga(int i, string d, string r) : id(i), disciplina(d), requisitos(r) {}
};

// Notificação
class Notificacao {
public:
    string mensagem;
    string data;
    Notificacao(string m) {
        mensagem = m;
        time_t agora = time(0);
        data = ctime(&agora);
        data.pop_back();
    }
};

// Controle Candidato
class CTRCandidato {
public:
    vector<Candidato> &candidatos;
    CTRCandidato(vector<Candidato> &c) : candidatos(c) {}

    void cadastrarCandidato() {
        int id = candidatos.size() + 1;
        string nome, cpf, email, senha;
        cout << "Nome: "; getline(cin, nome);
        cout << "CPF: "; getline(cin, cpf);
        cout << "Email: "; getline(cin, email);
        cout << "Senha: "; getline(cin, senha);
        candidatos.emplace_back(id, nome, cpf, email, senha);
        cout << "Candidato cadastrado com sucesso!\n";
    }

    Candidato* login() {
        string email, senha;
        cout << "Email: "; getline(cin, email);
        cout << "Senha: "; getline(cin, senha);
        for (auto &c : candidatos) {
            if (c.email == email && c.senha == senha) {
                cout << "Login realizado com sucesso!\n";
                return &c;
            }
        }
        cout << "Credenciais inválidas.\n";
        return nullptr;
    }
};

// Controle Vaga
class CTRVaga {
public:
    vector<Vaga> &vagas;
    CTRVaga(vector<Vaga> &v) : vagas(v) {}

    void criarVaga() {
        int id = vagas.size() + 1;
        string disciplina, requisitos;
        cout << "Disciplina: "; getline(cin, disciplina);
        cout << "Requisitos: "; getline(cin, requisitos);
        vagas.emplace_back(id, disciplina, requisitos);
        cout << "Vaga criada com sucesso!\n";
    }

    void listarVagas() {
        for (auto &v : vagas) {
            cout << "ID: " << v.id << " | " << v.disciplina << " | Requisitos: " << v.requisitos << " | Status: " << v.status << "\n";
        }
    }
};

// Controle Upload de Documentos
class CTRUploadDocumento {
public:
    void upload(Candidato &c) {
        string tipo, nome;
        cout << "Tipo do documento: "; getline(cin, tipo);
        cout << "Nome do arquivo: "; getline(cin, nome);
        c.adicionarDocumento(Documento(tipo, nome));
        cout << "Documento adicionado!\n";
    }
};

// Controle Status Processo
class CTRStatusProcesso {
public:
    void atualizarStatus(Candidato &c, const string &novoStatus) {
        c.status = novoStatus;
        cout << "Status atualizado para: " << novoStatus << "\n";
    }
};

// Controle Notificações
class CTRNotificacoes {
public:
    vector<Notificacao> notificacoes;
    void enviar(string msg) {
        notificacoes.emplace_back(msg);
    }
    void listar() {
        cout << "--- Notificações ---\n";
        for (auto &n : notificacoes) {
            cout << "[" << n.data << "] " << n.mensagem << "\n";
        }
    }
};

// Controle Banca (avaliação automática de candidatos)
class CTRBanca {
public:
    void avaliarCandidato(Candidato &c) {
        float total = 0;
        for (auto &d : c.documentos) {
            // Pontuação automática simples (baseado no tipo do documento)
            if (d.tipo == "Certificado") d.pontuacao = 10;
            else if (d.tipo == "Artigo") d.pontuacao = 5;
            else d.pontuacao = 2;
            total += d.pontuacao;
        }
        c.pontuacaoTotal = total;
        cout << "Pontuação calculada: " << total << "\n";
        if (total >= 15) c.status = "Aprovado";
        else c.status = "Reprovado";
    }
};


// Fronteira Candidato
class FRMCandidato {
    CTRVaga &ctrVaga;
    CTRUploadDocumento &ctrUpload;
    CTRStatusProcesso &ctrStatus;
    CTRNotificacoes &ctrNotif;
public:
    FRMCandidato(CTRVaga &v, CTRUploadDocumento &u, CTRStatusProcesso &s, CTRNotificacoes &n)
        : ctrVaga(v), ctrUpload(u), ctrStatus(s), ctrNotif(n) {}

    void menu(Candidato &c) {
        int opc;
        do {
            cout << "\n--- Menu Candidato ---\n";
            cout << "1. Ver Vagas\n2. Upload Documentos\n3. Ver Status\n4. Ver Notificações\n0. Sair\nEscolha: ";
            cin >> opc; cin.ignore();
            switch (opc) {
                case 1:
                    ctrVaga.listarVagas();
                    break;
                case 2:
                    ctrUpload.upload(c);
                    break;
                case 3:
                    cout << "Status atual: " << c.status << "\n";
                    break;
                case 4:
                    ctrNotif.listar();
                    break;
            }
        } while (opc != 0);
    }
};

// Fronteira Coordenador
class FRMCoordenador {
    CTRVaga &ctrVaga;
    CTRNotificacoes &ctrNotif;
public:
    FRMCoordenador(CTRVaga &v, CTRNotificacoes &n) : ctrVaga(v), ctrNotif(n) {}

    void menu() {
        int opc;
        do {
            cout << "\n--- Menu Coordenador ---\n";
            cout << "1. Criar Vaga\n2. Listar Vagas\n3. Ver Notificações\n0. Sair\nEscolha: ";
            cin >> opc; cin.ignore();
            switch (opc) {
                case 1:
                    ctrVaga.criarVaga();
                    ctrNotif.enviar("Nova vaga criada pelo Coordenador.");
                    break;
                case 2:
                    ctrVaga.listarVagas();
                    break;
                case 3:
                    ctrNotif.listar();
                    break;
            }
        } while (opc != 0);
    }
};

// Fronteira ATA
class FRMATA {
    CTRNotificacoes &ctrNotif;
public:
    FRMATA(CTRNotificacoes &n) : ctrNotif(n) {}
    void menu() {
        cout << "\n--- Menu ATA ---\n(Notificações disponíveis)\n";
        ctrNotif.listar();
    }
};

// Fronteira Diretor
class FRMDiretor {
    CTRNotificacoes &ctrNotif;
public:
    FRMDiretor(CTRNotificacoes &n) : ctrNotif(n) {}
    void menu() {
        cout << "\n--- Menu Diretor ---\n(Fiscalização de notificações)\n";
        ctrNotif.listar();
    }
};

// Simulação

void simulacaoAutomatica(vector<Candidato> &candidatos, vector<Vaga> &vagas) {
    // Criar vagas
    vagas.emplace_back(1, "Matemática", "Mestrado em Matemática");
    vagas.emplace_back(2, "Português", "Licenciatura em Letras");

    // Criar candidatos
    candidatos.emplace_back(1, "João", "123", "joao@mail", "123");
    candidatos.emplace_back(2, "Maria", "456", "maria@mail", "456");

    // Upload de documentos automático
    candidatos[0].adicionarDocumento(Documento("Certificado", "cert_joao.pdf"));
    candidatos[0].adicionarDocumento(Documento("Artigo", "artigo_joao.pdf"));
    candidatos[1].adicionarDocumento(Documento("Certificado", "cert_maria.pdf"));

    // Inscrever candidatos nas vagas
    vagas[0].candidatosInscritos.push_back(1);
    vagas[1].candidatosInscritos.push_back(2);
}

// Main

int main() {
    vector<Candidato> candidatos;
    vector<Vaga> vagas;
    CTRVaga ctrVaga(vagas);
    CTRUploadDocumento ctrUpload;
    CTRStatusProcesso ctrStatus;
    CTRNotificacoes ctrNotif;
    CTRCandidato ctrCand(candidatos);
    CTRBanca ctrBanca;

    // Simulação inicial automática
    simulacaoAutomatica(candidatos, vagas);

    int opc;
    do {
        cout << "\n--- Sistema de Processo Seletivo ---\n";
        cout << "1. Login Candidato\n2. Menu Coordenador\n3. Menu ATA\n4. Menu Diretor\n5. Cadastrar Candidato\n6. Avaliação Automática (Banca)\n0. Sair\nEscolha: ";
        cin >> opc; cin.ignore();
        switch (opc) {
            case 1: {
                Candidato* c = ctrCand.login();
                if (c) {
                    FRMCandidato frmCand(ctrVaga, ctrUpload, ctrStatus, ctrNotif);
                    frmCand.menu(*c);
                }
                break;
            }
            case 2: {
                FRMCoordenador frmCoord(ctrVaga, ctrNotif);
                frmCoord.menu();
                break;
            }
            case 3: {
                FRMATA frmAta(ctrNotif);
                frmAta.menu();
                break;
            }
            case 4: {
                FRMDiretor frmDir(ctrNotif);
                frmDir.menu();
                break;
            }
            case 5:
                ctrCand.cadastrarCandidato();
                break;
            case 6:
                // Avaliar todos candidatos
                for (auto &c : candidatos) ctrBanca.avaliarCandidato(c);
                break;
        }
    } while (opc != 0);

    return 0;
}
