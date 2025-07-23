#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <algorithm>

using namespace std;

// Disciplina
class Disciplina {
public:
    string nome;
    int creditos;
    vector<string> preRequisitos;

    Disciplina(string n, int c) : nome(n), creditos(c) {}
};

// Professor
class Professor {
public:
    string nome;
    vector<string> habilitacoes; // Disciplinas que pode lecionar

    Professor(string n) : nome(n) {}

    bool podeLecionar(string disciplina) {
        return find(habilitacoes.begin(), habilitacoes.end(), disciplina) != habilitacoes.end();
    }
};

// Aluno
class Aluno {
public:
    string nome;
    map<string, bool> historico;
    int reprovacoesSeguidas = 0;

    Aluno(string n) : nome(n) {}

    bool jaAprovado(string disciplina) {
        return historico.count(disciplina) && historico[disciplina] == true;
    }

    void registrarResultado(string disciplina, bool aprovado) {
        historico[disciplina] = aprovado;
        if (!aprovado) reprovacoesSeguidas++;
        else reprovacoesSeguidas = 0;
    }

    bool deveCancelarMatricula() {
        return reprovacoesSeguidas >= 3;
    }
};

// Sala
class Sala {
public:
    string nome;
    int capacidade;

    Sala(string n, int cap) : nome(n), capacidade(cap) {}
};

// Aula
class Aula {
public:
    string diaSemana;
    string horaInicio;
    string horaFim;
    Sala* sala;

    Aula(string dia, string inicio, string fim, Sala* s)
        : diaSemana(dia), horaInicio(inicio), horaFim(fim), sala(s) {}
};

// Turma
class Turma {
public:
    string codigo;
    Disciplina* disciplina;
    Professor* professor;
    vector<Aluno*> alunos;
    vector<Aula> aulas;
    int capacidade;

    Turma(string cod, Disciplina* d, Professor* p, int cap)
        : codigo(cod), disciplina(d), professor(p), capacidade(cap) {}

    bool temVaga() {
        return alunos.size() < capacidade;
    }

    bool verificarChoqueHorario(const Turma& outra) {
        for (auto &a1 : aulas) {
            for (auto &a2 : outra.aulas) {
                if (a1.diaSemana == a2.diaSemana) {
                    if (a1.horaInicio < a2.horaFim && a1.horaFim > a2.horaInicio) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
};

// Participação (ligação aluno-disciplina-turma)
class Participacao {
public:
    Aluno* aluno;
    Turma* turma;

    Participacao(Aluno* a, Turma* t) : aluno(a), turma(t) {}
};

// Inscrição
class Inscricao {
public:
    Aluno* aluno;
    Turma* turma;
    double nota1 = 0;
    double nota2 = 0;
    double exameFinal = 0;
    double frequencia = 100;
    bool aprovado = false;

    Inscricao(Aluno* a, Turma* t) : aluno(a), turma(t) {}

    void calcularResultado() {
        double NP = (nota1 + nota2) / 2.0;

        if (frequencia < 75) {
            aprovado = false;
            aluno->registrarResultado(turma->disciplina->nome, false);
            return;
        }

        if (NP >= 7) {
            aprovado = true;
        } else if (NP < 3) {
            aprovado = false;
        } else {
            double MF = (NP + exameFinal) / 2.0;
            aprovado = (MF >= 5);
        }
        aluno->registrarResultado(turma->disciplina->nome, aprovado);
    }
};

// Lista de Espera
class ListaEspera {
public:
    vector<Aluno*> fila;

    void adicionar(Aluno* aluno) {
        fila.push_back(aluno);
    }

    Aluno* sortearAluno() {
        if (fila.empty()) return nullptr;
        Aluno* escolhido = fila.front();
        fila.erase(fila.begin());
        return escolhido;
    }
};

// ControleAcademico (Controller)
class ControleAcademico {
public:
    vector<Aluno*> alunos;
    vector<Professor*> professores;
    vector<Disciplina*> disciplinas;
    vector<Turma*> turmas;

    // Validação limite de créditos
    bool validarLimiteCreditos(Aluno* aluno, vector<Turma*> turmasInscritas) {
        int soma = 0;
        for (auto t : turmasInscritas) {
            soma += t->disciplina->creditos;
        }
        return soma <= 20;
    }

    // Validação pré-requisitos
    bool validarPreRequisitos(Aluno* aluno, Disciplina* disciplina) {
        for (auto &pre : disciplina->preRequisitos) {
            if (!aluno->jaAprovado(pre)) return false;
        }
        return true;
    }

    // Realizar inscrição
    bool realizarInscricao(Aluno* aluno, Turma* turma, vector<Turma*> inscricoesAtuais) {
        if (!turma->temVaga()) {
            cout << "Turma sem vagas!\n";
            return false;
        }

        if (!validarPreRequisitos(aluno, turma->disciplina) || aluno->jaAprovado(turma->disciplina->nome)) {
            cout << "Nao cumpre requisitos ou já aprovado!\n";
            return false;
        }

        inscricoesAtuais.push_back(turma);
        if (!validarLimiteCreditos(aluno, inscricoesAtuais)) {
            cout << "Limite de créditos excedido!\n";
            return false;
        }

        for (auto t : inscricoesAtuais) {
            if (t != turma && turma->verificarChoqueHorario(*t)) {
                cout << "Choque de horário detectado!\n";
                return false;
            }
        }

        turma->alunos.push_back(aluno);
        cout << "Inscrição realizada com sucesso!\n";
        return true;
    }
};

// Main

int main() {
    ControleAcademico controle;

    // Disciplinas
    Disciplina* d1 = new Disciplina("Matematica", 4);
    Disciplina* d2 = new Disciplina("Fisica", 4);
    d2->preRequisitos.push_back("Matematica");
    controle.disciplinas.push_back(d1);
    controle.disciplinas.push_back(d2);

    // Professor
    Professor* p1 = new Professor("Carlos");
    p1->habilitacoes.push_back("Matematica");
    p1->habilitacoes.push_back("Fisica");
    controle.professores.push_back(p1);

    // Turmas
    Turma* t1 = new Turma("T1", d1, p1, 2);
    Turma* t2 = new Turma("T2", d2, p1, 2);
    controle.turmas.push_back(t1);
    controle.turmas.push_back(t2);

    // Sala e Aulas
    Sala* sala1 = new Sala("Lab1", 30);
    t1->aulas.push_back(Aula("Segunda", "08:00", "10:00", sala1));
    t2->aulas.push_back(Aula("Segunda", "09:00", "11:00", sala1)); // Choque

    // Aluno
    Aluno* a1 = new Aluno("Joao");
    controle.alunos.push_back(a1);

    // Inscrição
    vector<Turma*> inscricoes;
    controle.realizarInscricao(a1, t1, inscricoes); // OK
    inscricoes.push_back(t1);
    controle.realizarInscricao(a1, t2, inscricoes); // Falha: choque

    return 0;
}
