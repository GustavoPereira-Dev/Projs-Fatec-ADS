       IDENTIFICATION DIVISION.
       PROGRAM-ID. CONSAMG.
      *************************
      * CONSULTA DE AMIGOS    *
      *************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
                    DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
      *
.
       SELECT CADAMIGO ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS APELIDO
                    FILE STATUS  IS ST-ERRO
                    ALTERNATE RECORD KEY IS NOME WITH DUPLICATES.
      *
      *-----------------------------------------------------------------
       DATA DIVISION.
       FILE SECTION.
      *


       FD CADAMIGO
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADAMIGO.DAT".
       01 REGAMIGO.
          03 APELIDO       PIC X(12).
          03 NOME          PIC X(30).
          03 EMAIL         PIC X(30).
          03 SEXO          PIC X(01).
          03 GENERO        PIC X(01).
          03 TPAMIGO       PIC 9(01).
      *
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       01 W-SEL            PIC 9(01) VALUE ZEROS.
       01 W-CONT           PIC 9(04) VALUE ZEROS.
       01 W-OPCAO          PIC X(01) VALUE SPACES.
       01 ST-ERRO          PIC X(02) VALUE "00".
       01 W-ACT            PIC 9(02) VALUE ZEROS.
       01 MENS             PIC X(50) VALUE SPACES.
       01 LIMPA            PIC X(55) VALUE SPACES.
       01 SOLIC            PIC X(20) VALUE SPACES.
       01 CONLIN           PIC 9(03) VALUE 001.
       01 APELIDOENTR      PIC X(12) VALUE SPACES.
       01 NOMEENTR         PIC X(30) VALUE SPACES.

      *-----------------------------------------------------------------
      *
      *************************
      * REPORT SECTION *
      *************************
      *
       01 DET2.
          03 FILLER        PIC X(10) VALUE "APELIDO : ".
          03 DETAPELIDO    PIC X(12) VALUE SPACES.
          03 FILLER        PIC X(16) VALUE SPACES.
          03 FILLER        PIC X(07) VALUE "NOME : ".
          03 DETNOME       PIC X(30) VALUE SPACES.
          03 FILLER        PIC X(46) VALUE SPACES.

       01 DET3.
          03 FILLER        PIC X(08) VALUE "EMAIL : ".
          03 DETEMAIL      PIC X(30) VALUE SPACES.
          03 FILLER        PIC X(07) VALUE "SEXO : ".
          03 DETSEXO       PIC X(02) VALUE SPACES.
          03 FILLER        PIC X(09) VALUE "GENERO : ".
          03 DETGENERO     PIC X(01) VALUE SPACES.
          03 FILLER        PIC X(13) VALUE "TIPO AMIGO : ".
          03 DETTPAMIGO    PIC 9(01) VALUE ZEROS.
      *
      ******************
      * ROTINA DE TELA *
      ******************
      *
       SCREEN SECTION.

       01  TCONSAMG.
           05  BLANK SCREEN.
           05  LINE 02  COLUMN 01
               VALUE  "                             *** CONSULT".
           05  LINE 02  COLUMN 41
               VALUE  "A DE AMIGO ***".
           05  LINE 04  COLUMN 01
               VALUE  "   APELIDO :                       NOME".
           05  LINE 04  COLUMN 41
               VALUE  ":".
           05  LINE 22  COLUMN 01
               VALUE  "MENSAGEN :".
           05  TAPELIDOENTR
               LINE 04  COLUMN 14  PIC X(12)
               USING  APELIDOENTR
               HIGHLIGHT.
           05  TNOMEENTR
               LINE 04  COLUMN 43  PIC X(30)
               USING  NOMEENTR
               HIGHLIGHT.
      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
      *
       INC-OP1.
           OPEN INPUT  CADAMIGO
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "ARQUIVO DE CADAMIGO NAO EXISTE" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2
              ELSE
                 MOVE "ERRO ABERTURA DO ARQUIVO CADAMIGO"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2.
      *
       INC-001.
           MOVE ZEROS TO APELIDOENTR NOMEENTR
           DISPLAY  TCONSAMG.
       INC-001A.
           DISPLAY (22, 13)
            "SOLIC.=> F1=ENCERRA CONSULTA   F2=CONSULTA P/NOME."
           ACCEPT TAPELIDOENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADAMIGO
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001B.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE APELIDOENTR TO APELIDO
           START CADAMIGO KEY IS NOT LESS APELIDO INVALID KEY
                 MOVE "*** CHAVE NAO ENCONTRADA ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001.
           GO TO INC-RD2.
       INC-001B.
           DISPLAY (22, 13)
            "SOLIC.=> F1=ENCERRA CONSULTA     F2=CONSULTA P/APELIDO"
           ACCEPT TNOMEENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADAMIGO
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001A.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE NOMEENTR TO NOME
           START CADAMIGO KEY IS NOT LESS NOME INVALID KEY
                 MOVE "*** CHAVE NAO ENCONTRADA ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001B.
       INC-RD2.
           DISPLAY (22, 13) LIMPA
           READ CADAMIGO NEXT
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "10"
                 MOVE "*** FIM DO ARQUIVO  ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO NOV-SOL
              ELSE
                 MOVE "ERRO NA LEITURA DO ARQUIVO CADCEP"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
              NEXT SENTENCE.
       ROT-MONTAR.
           MOVE NOME         TO DETNOME
           MOVE APELIDO      TO DETAPELIDO
           MOVE EMAIL        TO DETEMAIL
           MOVE SEXO         TO DETSEXO
           MOVE GENERO       TO DETGENERO
           MOVE TPAMIGO      TO DETTPAMIGO
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 01) DET2
           ADD 1 TO CONLIN
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 01) DET3
           ADD 2 TO CONLIN
           IF CONLIN < 17
                  GO TO INC-RD2.
      *
       ROT-SOL.
           DISPLAY (22, 13) " *** CONTINUA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (22, 38) W-OPCAO  WITH UPDATE
           IF W-OPCAO = "S"
                  MOVE 01 TO CONLIN
                  DISPLAY TCONSAMG
                  GO TO INC-RD2.
           IF W-OPCAO NOT = "N"
                  MOVE "*** DIGITE S=SIM   N=NAO ***" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-SOL.
           MOVE 01 TO CONLIN.
           GO TO INC-001.
      *
       NOV-SOL.
           DISPLAY (22, 13) " *** NOVA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (22, 34) W-OPCAO WITH UPDATE
           IF W-OPCAO = "S"
                  MOVE 01 TO CONLIN
                  GO TO INC-001.
           IF W-OPCAO NOT = "N"
                  MOVE "*** DIGITE S=SIM   N=NAO ***" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO NOV-SOL.
      *
      **********************
      * ROTINA DE FIM      *
      **********************
      *
       ROT-FIM.
           DISPLAY (01 01) ERASE.
           CLOSE CADAMIGO.
       ROT-FIM2.
           EXIT PROGRAM.
       ROT-FIM3.
           STOP RUN.
      *
      **********************
      * ROTINA DE MENSAGEM *
      **********************
      *
       ROT-MENS.
           MOVE ZEROS TO W-CONT.
       ROT-MENS1.
           DISPLAY (22, 13) MENS.
       ROT-MENS2.
           ADD 1 TO W-CONT
           IF W-CONT < 1000
              GO TO ROT-MENS2
           ELSE
              DISPLAY (22, 12) LIMPA.
       ROT-MENS-FIM.
           EXIT.
       FIM-ROT-MENS.
      *

      *---------------------*** FIM DE PROGRAMA ***--------------------*
