       IDENTIFICATION DIVISION.
       PROGRAM-ID. PROGRX10X.
       AUTHOR. GUSTAVO PEREIRA.
      ************************
      * CADASTRO DE AMIGOS   *
      ************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
             DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       SELECT CADAMIGO ASSIGN TO DISK
                    ORGANIZATION IS SEQUENTIAL
                    ACCESS MODE  IS SEQUENTIAL.
      *
      *-----------------------------------------------------------------
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
      *
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       01 W-OPCAO       PIC X(01) VALUE SPACES.
       01 W-ACT         PIC 9(02) VALUE ZEROS.
       01 MASCDATA      PIC 99.99.9999.
      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
           OPEN OUTPUT CADAMIGO.
      *------------- INICIALIZACAO DAS VARIAVEIS------------                
       R1.
           MOVE SPACES TO APELIDO NOME EMAIL SEXO GENERO
           MOVE ZEROS TO TPAMIGO DATANASC.
      *-------------MONTAGEM DA TELA------------------------
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
      *----------ENTRADA DE DADOS---------------
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
       R4.
           ACCEPT (08, 18) EMAIL.
           IF EMAIL = SPACES 
              GO TO R4.
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
           DISPLAY (23, 20) "                                "
           ACCEPT (12, 18) GENERO.
           ACCEPT (14, 18) TPAMIGO.
           ACCEPT (16, 18) DATANASC.
           MOVE DATANASC1 TO MASCDATA
           DISPLAY (16, 18) MASCDATA.
       R99.
           DISPLAY (22, 40) "GRAVAR S/N : "
           ACCEPT (22, 53) W-OPCAO
           IF W-OPCAO = "N"
              DISPLAY (23, 20) "DADOS RECUSADOS PELO USUARIO"
              STOP "     "
              GO TO R1
           ELSE
                 IF W-OPCAO NOT = "S"
                   DISPLAY (23, 20) "DIGITE S=SIM ou N=NAO "
                   GO TO R99.
       INC-WR1.
           WRITE REGAMIGO.
           DISPLAY (23, 20) "  ***  DADOS GRAVADOS COM SUCESSO "
           STOP "    "
           GO TO R1.
       ROT-FIM.
           CLOSE CADAMIGO.

 

      *    FILE STATUS
      *    00 = OPERA  O REALIZADO COM SUCESSO
      *    22 = REGISTRO J  CADASTRADO
      *    23 = REGISTRO N O ENCONTRADO
      *    30 = ARQUIVO N O ENCONTRADO
      

