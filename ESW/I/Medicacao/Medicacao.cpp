#include <iostream>
#include <string>
#include <vector>
#include <ctime>

using namespace std;

// Medicacao

class Medicacao {
public:
    string nomeRemedio;
    string nomePaciente;
    time_t dataInicio;
    int diasPrescritos;
    int vezesAoDia;
    double dosagem;

    Medicacao(string nr, string np, time_t di, int dp, int vd, double d)
        : nomeRemedio(nr), nomePaciente(np), dataInicio(di), diasPrescritos(dp), vezesAoDia(vd), dosagem(d) {}
};

// Horario

class Horario {
public:
    vector<time_t> horarios;

    void sugerirHorarios(Medicacao m) {
        time_t intervalo = 24 * 3600 / m.vezesAoDia;
        for (int i = 0; i < m.vezesAoDia; i++) {
            time_t horario = m.dataInicio + i * intervalo;
            horarios.push_back(horario);
        }
    }

    void imprimirHorarios() {
        for (time_t h : horarios) {
            cout << "Horario: " << ctime(&h);
        }
    }
};

// Alarme

class Alarme {
public:
    void emitirAlarme(time_t horario) {
        time_t agora = time(0);
        if (difftime(horario, agora) <= 15 * 60) {
            cout << "Alarme: Esta quase na hora de tomar seu remedio!" << endl;
        }
    }

    void confirmarIngestao(bool confirmacao) {
        if (confirmacao) {
            cout << "Confirmacao: Voce confirmou que tomou o remedio." << endl;
        } else {
            cout << "Lembrete: Nao se esqueca de tomar seu remedio!" << endl;
        }
    }
};

// Usuario

class Usuario {
public:
    vector<Medicacao> medicacoes;
    Horario horario;
    Alarme alarme;

    void cadastrarMedicacao(Medicacao m) {
        medicacoes.push_back(m);
        horario.sugerirHorarios(m);
    }

    void listarHorarios() {
        horario.imprimirHorarios();
    }

    void emitirAlarme(time_t horario) {
        alarme.emitirAlarme(horario);
    }

    void confirmarIngestao(bool confirmacao) {
        alarme.confirmarIngestao(confirmacao);
    }
};

// Main

void teste() {
    Usuario usuario;

    // Exemplo de cadastro de medicacao
    time_t dataInicio = time(0); // Data atual
    Medicacao medicacao1("Paracetamol", "Mauricio", dataInicio, 7, 3, 500);
    usuario.cadastrarMedicacao(medicacao1);

    // Listar horários sugeridos
    usuario.listarHorarios();

    // Simular o alarme e confirmação de ingestão
    time_t proximoHorario = usuario.horario.horarios[0];
    usuario.emitirAlarme(proximoHorario);

    // Confirmar ingestao
    usuario.confirmarIngestao(true);

    return 0;
}

int main() {
    Usuario usuario;
    int opcao;

    do {
        cout << "\n=== GERENCIADOR DE MEDICACAO ===\n";
        cout << "1. Cadastrar medicacao\n";
        cout << "2. Listar horarios sugeridos\n";
        cout << "3. Emitir alarme para proximo horario\n";
        cout << "4. Confirmar ingestao\n";
        cout << "5. Teste de sistema\n";
        cout << "0. Sair\n";
        cout << "Escolha uma opcao: ";
        cin >> opcao;
        cin.ignore();

        switch (opcao) {
            case 1: {
                string nomeRemedio, nomePaciente;
                int dias, vezes;
                double dosagem;

                cout << "Nome do remedio: "; getline(cin, nomeRemedio);
                cout << "Nome do paciente: "; getline(cin, nomePaciente);
                cout << "Dias prescritos: "; cin >> dias;
                cout << "Vezes ao dia: "; cin >> vezes;
                cout << "Dosagem (mg): "; cin >> dosagem;
                time_t inicio = time(0); // Agora

                Medicacao m(nomeRemedio, nomePaciente, inicio, dias, vezes, dosagem);
                usuario.cadastrarMedicacao(m);
                cout << "Medicacao cadastrada.\n";
                break;
            }

            case 2:
                usuario.listarHorarios();
                break;

            case 3: {
                if (!usuario.horario.horarios.empty()) {
                    usuario.emitirAlarme(usuario.horario.horarios[0]);
                } else {
                    cout << "Nenhum horario cadastrado.\n";
                }
                break;
            }

            case 4: {
                char resp;
                cout << "Voce tomou o remedio? (s/n): ";
                cin >> resp;
                bool confirmacao = (resp == 's' || resp == 'S');
                usuario.confirmarIngestao(confirmacao);
                break;
            }

            case 5: {
                cout << "Executando teste de sistema...\n";
                teste();
                break;
            }

            case 0:
                cout << "Saindo do sistema...\n";
                break;

            default:
                cout << "Opcao invalida.\n";
        }

    } while (opcao != 0);

    return 0;
}