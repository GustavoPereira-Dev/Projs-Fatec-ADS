import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Aluno {
    long id;
    Date nascimento;
    String ra;
    String nome;

    public Aluno(long id, Date nascimento, String ra, String nome) {
        this.id = id;
        this.nascimento = nascimento;
        this.ra = ra;
        this.nome = nome;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "ID: " + id + "\nNome: " + nome + "\nRA: " + ra + "\nNascimento: " + sdf.format(nascimento);
    }
}