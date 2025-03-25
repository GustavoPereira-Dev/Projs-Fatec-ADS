       IDENTIFICATION DIVISION.
       PROGRAM-ID. PROGR0X6.
       AUTHOR. GUSTAVO.
      ************************
      * CADASTRO DE AMIGOS   *
      ************************
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
             DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       SELECT CADAMIGO ASSIGN TO DISK
                    ORGANIZATION IS SEQUENTIAL
                    ACCESS MODE  IS SEQUENTIAL.

       DATA DIVISION.
       FILE SECTION.
       FD CADAMIGO
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADAMIG1.DAT". 
       01 REGAMIGO.
          03 APELIDO       PIC X(12).
          03 NOME          PIC X(30).
          03 EMAIL         PIC X(30).
          03 SEXO          PIC X(01).
          03 GENERO        PIC X(01).
          03 TPAMIGO       PIC 9(01).
          03 DATANASC.
             04 DIA        PIC 9(02).
             04 MES        PIC 9(02).
             04 ANO.
                05 SEC     PIC 9(02).
                05 DSEC    PIC 9(02).
          03 DATANASC1 REDEFINES DATANASC PIC 9(08).

       WORKING-STORAGE SECTION.
       01 W-OPCAO       PIC X(01) VALUE SPACES.
       01 W-ACT         PIC 9(02) VALUE ZEROS.
       01 MASCDATA      PIC 99.99.9999.
       01 W-NOME        PIC X(30) VALUE SPACES.
       01 W-PROVEDOR    PIC X(30) VALUE SPACES.
       01 W-POINTER     PIC 9(02) VALUE ZEROS.
       01 W-MAX-DIA     PIC 99 VALUE ZEROS.

      *------------------- PROCEDURE DIVISION -------------------

       PROCEDURE DIVISION.
       INICIO.
           OPEN OUTPUT CADAMIGO.

       R1.
           MOVE SPACES TO APELIDO NOME EMAIL SEXO GENERO.
           MOVE ZEROS TO TPAMIGO DATANASC.

       RTELA.
           DISPLAY ERASE
           DISPLAY (01, 30) "*** CADASTRO DE AMIGOS ***"
           DISPLAY (04, 05) "APELIDO     : "
           DISPLAY (06, 05) "NOME        : "
           DISPLAY (08, 05) "EMAIL       : "
           DISPLAY (10, 05) "SEXO        : "
           DISPLAY (12, 05) "GENERO      : "
           DISPLAY (14, 05) "TIPO AMIGO  : "
           DISPLAY (16, 05) "DATA NASC.  : "
           DISPLAY (23, 02) "MENSAGEM : ".

       R2.
           ACCEPT (04, 18) APELIDO.
           IF APELIDO = "FIM"
              GO TO ROT-FIM.
           IF APELIDO = SPACES 
              GO TO R2.
       R3.
           ACCEPT (06, 18) NOME.
           IF NOME = SPACES 
              GO TO R3.

       R4-VALIDA-EMAIL.
           DISPLAY (23, 20) "DIGITE UM EMAIL VÁLIDO (FORMATO: nome@provedor):"
           ACCEPT (08, 18) EMAIL.
           INSPECT EMAIL TALLYING W-ACT FOR ALL "@".
           IF W-ACT NOT = 1
              DISPLAY (23, 20) "EMAIL INVÁLIDO! AUSÊNCIA DE '@'"
              GO TO R4-VALIDA-EMAIL.
           MOVE ZEROS TO W-ACT.
           UNSTRING EMAIL DELIMITED BY "@" INTO W-NOME W-PROVEDOR
               WITH POINTER W-POINTER.
           IF W-NOME = SPACES OR W-PROVEDOR = SPACES
              DISPLAY (23, 20) "EMAIL INVÁLIDO! FORMATO INCORRETO"
              GO TO R4-VALIDA-EMAIL.
           INSPECT W-PROVEDOR TALLYING W-ACT FOR ALL ".".
           IF W-ACT < 1
              DISPLAY (23, 20) "EMAIL INVÁLIDO! FALTA PONTO (.) NO PROVEDOR"
              GO TO R4-VALIDA-EMAIL.
           DISPLAY (23, 20) "                                ".

       R5.
           DISPLAY (23, 20) "DIGITE M=MASCULINO ou F=FEMININO"
           ACCEPT (10, 18) SEXO.
           IF SEXO = "M" OR "m"
              DISPLAY (10, 20) "MASCULINO"
           ELSE
              IF SEXO = "F" OR "f"
                 DISPLAY (10, 20) "FEMININO"
              ELSE
                 GO TO R5.

       R6-VALIDA-GENERO.
           DISPLAY (23, 20) "DIGITE UM GÊNERO VÁLIDO"
           ACCEPT (12, 18) GENERO.
           EVALUATE GENERO
               WHEN "L" DISPLAY "Lésbica"
               WHEN "G" DISPLAY "Gay"
               WHEN "B" DISPLAY "Bissexual"
               WHEN "T" DISPLAY "Transsexual"
               WHEN "Q" DISPLAY "Queer"
               WHEN "I" DISPLAY "Intersexual"
               WHEN "A" DISPLAY "Assexual"
               WHEN "P" DISPLAY "Pansexual"
               WHEN "F" DISPLAY "Fluido"
               WHEN "H" DISPLAY "Heterosexual"
               WHEN "D" DISPLAY "Andrógino"
               WHEN "+" DISPLAY "Outro gênero/orientação"
               WHEN "N" DISPLAY "Não-binário"
               WHEN OTHER
                  DISPLAY "Gênero inválido, tente novamente."
                  GO TO R6-VALIDA-GENERO
           END-EVALUATE.

       R7-VALIDA-TPAMIGO.
           ACCEPT (14, 18) TPAMIGO.
           EVALUATE TPAMIGO
               WHEN 0 DISPLAY "Vizinho(a)"
               WHEN 1 DISPLAY "Melhor amigo(a)"
               WHEN 2 DISPLAY "Amigo(a) próximo(a)"
               WHEN 3 DISPLAY "Amigo"
               WHEN 4 DISPLAY "Colega da escola"
               WHEN 5 DISPLAY "Colega da faculdade"
               WHEN 6 DISPLAY "Colega de trabalho"
               WHEN 7 DISPLAY "Amigo(a) de um amigo(a)"
               WHEN 8 DISPLAY "Conhecido(a)"
               WHEN 9 DISPLAY "Pouco conhecido(a)"
               WHEN 10 DISPLAY "Amigo(a) virtual"
               WHEN OTHER
                  DISPLAY "Tipo de amigo inválido, tente novamente."
                  GO TO R7-VALIDA-TPAMIGO
           END-EVALUATE.

       R8-VALIDA-DATA.
           ACCEPT (16, 18) DATANASC.
           IF MES < 1 OR MES > 12
              DISPLAY "MÊS INVÁLIDO!" GO TO R8-VALIDA-DATA.
           IF DIA < 1 OR (MES = 2 AND DIA > 29)
              DISPLAY "DIA INVÁLIDO!" GO TO R8-VALIDA-DATA.
           IF (MES = 2 AND DIA = 29) AND (ANO MOD 4 NOT = 0 OR (ANO MOD 100 = 0 AND ANO MOD 400 NOT = 0))
              DISPLAY "ANO NÃO É BISSEXTO!" GO TO R8-VALIDA-DATA.
           IF (MES IN 1 3 5 7 8 10 12) AND DIA > 31
              DISPLAY "DIA INVÁLIDO!" GO TO R8-VALIDA-DATA.
           IF (MES IN 4 6 9 11) AND DIA > 30
              DISPLAY (01, 30) "DIA INVALIDO!" 
	      GO TO R8-VALIDA-DATA.
           MOVE DATANASC1 TO MASCDATA
           DISPLAY (16, 18) MASCDATA.

       R99.
           DISPLAY (22, 40) "GRAVAR S/N : "
           ACCEPT (22, 53) W-OPCAO
           IF W-OPCAO = "N"
              DISPLAY (23, 20) "DADOS RECUSADOS PELO USUÁRIO"
              STOP "     "
              GO TO R1
           ELSE
              IF W-OPCAO NOT = "S"
                 DISPLAY (23, 20) "DIGITE S=SIM ou N=NÃO"
                 GO TO R99.

       INC-WR1.
           WRITE REGAMIGO.
           DISPLAY (23, 20) "  ***  DADOS GRAVADOS COM SUCESSO ***"
           STOP "    "
           GO TO R1.

       ROT-FIM.
           CLOSE CADAMIGO.
