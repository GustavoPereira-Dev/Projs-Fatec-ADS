#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <memory>

using namespace std;

// Ator
class Ator {
protected:
    int id;
    string nome;
public:
    Ator(int i, string n) : id(i), nome(n) {}
    virtual string getTipo() const = 0;
    int getId() const { return id; }
    string getNome() const { return nome; }
};

// Candidato (Ator)
class Candidato : public Ator {
public:
    bool minoria; // RD02
    bool concursado; // RD07
    vector<string> documentos;
    string status;
    Candidato(int i, string n, bool m=false, bool conc=false) 
        : Ator(i,n), minoria(m), concursado(conc), status("Não inscrito") {}
    string getTipo() const override { return "Candidato"; }
};

// Coordenador (Ator)
class Coordenador : public Ator {
public:
    Coordenador(int i, string n) : Ator(i,n) {}
    string getTipo() const override { return "Coordenador"; }
};

// ATA (Ator)
class ATA : public Ator {
public:
    ATA(int i, string n) : Ator(i,n) {}
    string getTipo() const override { return "ATA"; }
};

// Diretor (Ator)
class Diretor : public Ator {
public:
    Diretor(int i, string n) : Ator(i,n) {}
    string getTipo() const override { return "Diretor"; }
};

// Vaga

class Vaga {
protected:
    int id;
    string disciplina;
    string requisitos;
    bool aberta;
    string tipo; // PSS ou Concurso
    vector<int> candidatosInscritos;
    bool interna; // RD05
public:
    Vaga(int i, string d, string r, string t, bool interna = true)
        : id(i), disciplina(d), requisitos(r), aberta(true), tipo(t), interna(interna) {}

    int getId() const { return id; }
    string getDisciplina() const { return disciplina; }
    string getTipo() const { return tipo; }
    string getRequisitos() const { return requisitos; }
    bool isAberta() const { return aberta; }
    bool isInterna() const { return interna; }

    void fechar() { aberta = false; }
    void mudarParaExterna() { interna = false; tipo = "PSS"; } // RD05

    void inscreverCandidato(int idCandidato) {
        candidatosInscritos.push_back(idCandidato);
    }

    const vector<int>& getCandidatos() const { return candidatosInscritos; }
};

// Notificação

class Notificacao {
public:
    string mensagem;
    string destino; // "Todos" ou tipo específico
    bool lida = false;

    Notificacao(string msg, string dest) : mensagem(msg), destino(dest) {}
};

// Armenamento Global

vector<shared_ptr<Vaga>> vagas;
vector<Candidato> candidatos;
vector<Notificacao> notificacoes;

// Funções auxiliares

Candidato* buscarCandidatoPorId(int id) {
    for (auto &c : candidatos) if (c.getId() == id) return &c;
    return nullptr;
}

shared_ptr<Vaga> buscarVagaPorId(int id) {
    for (auto &v : vagas) if (v->getId() == id) return v;
    return nullptr;
}

// Vaga Controller

class CTRVaga {
public:
    void criarVaga(int id, string disciplina, string requisitos, string tipo, bool interna = true) {
        // RD04: Verificação de docente atual (se < 6 meses, não pode criar vaga)
        bool docenteOcupado = false; // Simulação: se fosse true, impediria
        if (docenteOcupado) {
            cout << "[ERRO] Não é possível criar vaga. Docente atual ainda possui contrato ativo (>6 meses)\n";
            return;
        }
        vagas.push_back(make_shared<Vaga>(id, disciplina, requisitos, tipo, interna));
        notificacoes.push_back(Notificacao("Nova vaga criada: " + disciplina, "Todos"));
    }

    void listarVagas() {
        cout << "\n=== VAGAS DISPONÍVEIS ===\n";
        for (auto &vaga : vagas) {
            cout << "ID: " << vaga->getId() << " | " << vaga->getDisciplina()
                 << " | Tipo: " << vaga->getTipo()
                 << " | Status: " << (vaga->isAberta() ? "Aberta" : "Fechada")
                 << (vaga->isInterna() ? " | Interna" : " | Externa") << "\n";
        }
    }

    void fecharVaga(int id) {
        auto vaga = buscarVagaPorId(id);
        if (vaga) {
            // RD06: Concurso público não pode ser fechado manualmente
            if (vaga->getTipo() == "Concurso Público") {
                cout << "[ERRO] Não é permitido fechar vagas de concurso público!\n";
                return;
            }
            vaga->fechar();
            notificacoes.push_back(Notificacao("Vaga " + vaga->getDisciplina() + " foi fechada", "Todos"));
        } else cout << "Vaga não encontrada.\n";
    }

    void mudarParaExterna(int id) {
        auto vaga = buscarVagaPorId(id);
        if (vaga && vaga->isInterna()) {
            vaga->mudarParaExterna();
            notificacoes.push_back(Notificacao("Vaga interna mudou para externa (PSS): " + vaga->getDisciplina(), "Todos"));
        }
    }
};

// Candidato Controller

class CTRCandidato {
public:
    void cadastrarCandidato(int id, string nome, bool minoria=false, bool concursado=false) {
        candidatos.emplace_back(id, nome, minoria, concursado);
        notificacoes.push_back(Notificacao("Novo candidato cadastrado: " + nome, "Coordenador"));
    }

    void listarCandidatos() {
        cout << "\n=== CANDIDATOS CADASTRADOS ===\n";
        for (auto &c : candidatos) {
            cout << "ID: " << c.getId() << " | " << c.getNome() << " | Status: " << c.status
                 << (c.minoria ? " | Minoria" : "")
                 << (c.concursado ? " | Concursado" : "") << "\n";
        }
    }

    void inscreverEmVaga(int idCand, int idVaga) {
        auto cand = buscarCandidatoPorId(idCand);
        auto vaga = buscarVagaPorId(idVaga);
        if (!cand || !vaga) {
            cout << "[ERRO] Candidato ou Vaga inválidos!\n";
            return;
        }
        vaga->inscreverCandidato(idCand);
        cand->status = "Inscrito na vaga " + vaga->getDisciplina();
        notificacoes.push_back(Notificacao("Candidato " + cand->getNome() + " inscrito em " + vaga->getDisciplina(), "Coordenador"));
    }

    void enviarDocumento(int idCand, string doc) {
        auto cand = buscarCandidatoPorId(idCand);
        if (cand) {
            cand->documentos.push_back(doc);
            notificacoes.push_back(Notificacao("Documento '" + doc + "' enviado por " + cand->getNome(), "ATA"));
        }
    }

    void exibirStatus(int idCand) {
        auto cand = buscarCandidatoPorId(idCand);
        if (cand) cout << "Status de " << cand->getNome() << ": " << cand->status << "\n";
    }
};

// Documentos Controller

class CTRDocumentos {
public:
    void validarDocumentos(int idCand) {
        auto cand = buscarCandidatoPorId(idCand);
        if (!cand) { cout << "Candidato não encontrado.\n"; return; }
        if (cand->documentos.empty()) {
            cout << "Nenhum documento para validar.\n";
            return;
        }
        notificacoes.push_back(Notificacao("Documentos do candidato " + cand->getNome() + " validados", "Candidato"));
        cand->status = "Documentos Validados";
    }
};

// Notificacoes Controller

class CTRNotificacoes {
public:
    void exibirNotificacoes(string ator) {
        cout << "\n=== NOTIFICAÇÕES PARA " << ator << " ===\n";
        for (auto &n : notificacoes) {
            if (n.destino == ator || n.destino == "Todos") {
                cout << "- " << n.mensagem << (n.lida ? " (lida)" : "") << "\n";
                n.lida = true;
            }
        }
    }
};

// Main

int main() {
    CTRVaga ctrVaga;
    CTRCandidato ctrCandidato;
    CTRDocumentos ctrDocs;
    CTRNotificacoes ctrNotif;

    int opcao;
    do {
        cout << "\n===== MENU PRINCIPAL =====\n";
        cout << "1. Criar Vaga\n2. Listar Vagas\n3. Fechar Vaga\n4. Mudar Vaga Interna para Externa\n";
        cout << "5. Cadastrar Candidato\n6. Listar Candidatos\n7. Inscrever Candidato em Vaga\n";
        cout << "8. Enviar Documento\n9. Validar Documentos (ATA)\n10. Ver Status Candidato\n";
        cout << "11. Ver Notificações\n0. Sair\nEscolha: ";
        cin >> opcao;

        switch (opcao) {
            case 1: {
                int id; string disc, req, tipo; char inter;
                cout << "ID da vaga: "; cin >> id;
                cin.ignore();
                cout << "Disciplina: "; getline(cin, disc);
                cout << "Requisitos: "; getline(cin, req);
                cout << "Tipo (PSS/Concurso Público): "; getline(cin, tipo);
                cout << "É interna? (s/n): "; cin >> inter;
                ctrVaga.criarVaga(id, disc, req, tipo, (inter=='s'));
                break;
            }
            case 2: ctrVaga.listarVagas(); break;
            case 3: {
                int id; cout << "ID da vaga para fechar: "; cin >> id;
                ctrVaga.fecharVaga(id); break;
            }
            case 4: {
                int id; cout << "ID da vaga para mudar para externa: "; cin >> id;
                ctrVaga.mudarParaExterna(id); break;
            }
            case 5: {
                int id; string nome; char min, conc;
                cout << "ID do candidato: "; cin >> id;
                cin.ignore();
                cout << "Nome: "; getline(cin, nome);
                cout << "É minoria? (s/n): "; cin >> min;
                cout << "É concursado? (s/n): "; cin >> conc;
                ctrCandidato.cadastrarCandidato(id, nome, (min=='s'), (conc=='s'));
                break;
            }
            case 6: ctrCandidato.listarCandidatos(); break;
            case 7: {
                int idC, idV;
                cout << "ID candidato: "; cin >> idC;
                cout << "ID vaga: "; cin >> idV;
                ctrCandidato.inscreverEmVaga(idC, idV); break;
            }
            case 8: {
                int id; string doc;
                cout << "ID candidato: "; cin >> id;
                cin.ignore();
                cout << "Nome do documento: "; getline(cin, doc);
                ctrCandidato.enviarDocumento(id, doc); break;
            }
            case 9: {
                int id; cout << "ID candidato: "; cin >> id;
                ctrDocs.validarDocumentos(id); break;
            }
            case 10: {
                int id; cout << "ID candidato: "; cin >> id;
                ctrCandidato.exibirStatus(id); break;
            }
            case 11:
                ctrNotif.exibirNotificacoes("Todos"); break;
            case 0:
                cout << "Saindo...\n"; break;
            default:
                cout << "Opção inválida!\n";
        }
    } while (opcao != 0);

    return 0;
}
