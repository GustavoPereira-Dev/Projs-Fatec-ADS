       IDENTIFICATION DIVISION.
       PROGRAM-ID. PROGR0B1.
      *AUTOR. GUSTAVO PEREIRA.
      *******************************************
      * MANUTENCAO DO PROGRAMA 0  *
      *******************************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
                         DECIMAL-POINT IS COMMA.
      *
      *-----------------------------------------------------------------
       DATA DIVISION.
      *
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       01 APELIDO     PIC X(12) VALUE SPACES.
       01 NOME          PIC X(30) VALUE SPACES.
       01 IDADE          PIC 9(03) VALUE ZEROS.
	   01 CONTATO          PIC 9(13) VALUE ZEROS.
	   01 HOBBY               PIC X(30) VALUE SPACES.
	   

      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
      *
           DISPLAY (02, 25) "CADASTRO DE AMIGOS".
           DISPLAY (06, 10) "APELIDO  :".
           DISPLAY (08, 10) "NOME   :"
           DISPLAY (10, 10) "IDADE   :".
		   DISPLAY (12, 10) "CONTATO   :".
		   DISPLAY (14, 10) "HOBBY   :".
		   
       ROT-APELIDO.
           ACCEPT (06, 20) APELIDO.
           IF APELIDO = SPACES
               GO TO ROT-APELIDO.
       ROT-NOME.	   		
           ACCEPT (08, 20) NOME
           IF APELIDO = SPACES
               GO TO ROT-NOME.
       ROT-IDADE.
           ACCEPT (10, 20) IDADE
           IF APELIDO = ZEROS
               GO TO ROT-IDADE.
       ROT-CONTATO.
		   ACCEPT (12, 20) CONTATO
           IF APELIDO = ZEROS
               GO TO ROT-CONTATO.
       ROT-HOBBY.
           ACCEPT (14, 20) HOBBY.
           IF HOBBY = SPACES
               GO TO ROT-HOBBY.

.          STOP RUN.
