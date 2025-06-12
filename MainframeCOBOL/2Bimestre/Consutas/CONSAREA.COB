       IDENTIFICATION DIVISION.
       PROGRAM-ID. SMP005.
       AUTHOR. GUSTAVO PEREIRA.
      ***********************************************
      * MANUTENCAO DO CADASTRO AREA   *
      ***********************************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
                     DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       SELECT CADAREA ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS AREA-COD
                    FILE STATUS  IS ST-ERRO
                    ALTERNATE RECORD KEY IS AREA-NOME
                                   WITH DUPLICATES.
      *
      *-----------------------------------------------------------------
       DATA DIVISION.
       FILE SECTION.
       FD CADAREA
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADAREA.DAT".
       01 REGAREA.
                03 AREA-COD          PIC 9(03).
                03 AREA-NOME         PIC X(20).
                03 AREA-ESTRUTURA    PIC 9(1).

      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       77 W-SEL             PIC 9(01) VALUE ZEROS.
       77 W-CONT            PIC 9(06) VALUE ZEROS.
       77 W-OPCAO           PIC X(01) VALUE SPACES.
       77 ST-ERRO           PIC X(02) VALUE "00".
       77 W-ACT             PIC 9(02) VALUE ZEROS.
       77 MENS              PIC X(60) VALUE SPACES.
       77 LIMPA             PIC X(60) VALUE SPACES.
       01 SOLIC             PIC X(20) VALUE SPACES.
       01 CONLIN            PIC 9(03) VALUE 001.
       77 AREA-CODENTR      PIC 9(03) VALUE ZEROS.
       77 AREA-NOMEENTR     PIC X(20) VALUE SPACES.
      *-----------------------------------------------------------------
      *
      ******************
      * REPORT SECTION *
      ******************
      *
       01 DET2.
            03 FILLER                 PIC X(05) VALUE SPACES.
            03 DETAREA-COD          PIC 9(03).
            03 FILLER                 PIC X(07) VALUE SPACES.
            03 DETAREA-NOME         PIC X(20).
            03 FILLER                 PIC X(14) VALUE SPACES.
            03 DETAREA-ESTRUTURA    PIC 9(1).
      *-----------------------------------------------------------------


       SCREEN SECTION.

       01  TCONSAREA1.
           05  BLANK SCREEN.
           05  LINE 02  COLUMN 01
               VALUE  "                           *** CONSULTA".
           05  LINE 02  COLUMN 41
               VALUE  "DE AREA ***".
           05  LINE 04  COLUMN 01
               VALUE  "    CODIGO                NOME".
           05  LINE 04  COLUMN 41
               VALUE  "     ESTRUTURA".
           05  LINE 22  COLUMN 01
               VALUE  "   MENSAGEM :".
           05  TAREA-CODENTR
               LINE 06  COLUMN 06  PIC 9(03)
               USING  AREA-CODENTR
               HIGHLIGHT.
           05  TAREA-NOMEENTR
               LINE 06  COLUMN 17  PIC X(20)
               USING  AREA-NOMEENTR
               HIGHLIGHT.
      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
      *
       INC-OP1.
           OPEN INPUT  CADAREA
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "ARQUIVO DE CADAREA NAO EXISTE" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2
              ELSE
                 MOVE "ERRO ABERTURA DO ARQUIVO CADAREA"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2.
      *
       INC-001.
           MOVE ZEROS TO AREA-CODENTR
           MOVE SPACES TO AREA-NOMEENTR
           DISPLAY  TCONSAREA1.
       INC-001A.
           DISPLAY (22, 15)
            "SOLIC.=> F1=ENCERRA CONSULTA   F2=CONSULTA P/LOGRADOURO."
           ACCEPT TAREA-CODENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADAREA
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001B.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE AREA-CODENTR TO AREA-COD
           START CADAREA KEY IS NOT LESS AREA-COD INVALID KEY
                 MOVE "*** CHAVE NAO ENCONTRADA ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001.
           GO TO INC-RD2.
       INC-001B.
           DISPLAY (22, 15)
            "SOLIC.=> F1=ENCERRA CONSULTA     F2=CONSULTA P/NOME "
           ACCEPT TAREA-NOMEENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADAREA
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001A.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE AREA-NOMEENTR TO AREA-NOME
           START CADAREA KEY IS NOT LESS AREA-NOME INVALID KEY
                 MOVE "*** CHAVE NAO ENCONTRADA ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001B.
       INC-RD2.
           DISPLAY (23, 15) LIMPA
           READ CADAREA NEXT
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "10"
                 MOVE "*** FIM DO ARQUIVO  ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO NOV-SOL
              ELSE
                 MOVE "ERRO NA LEITURA DO ARQUIVO CADAREA"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
              NEXT SENTENCE.
       ROT-MONTAR.
           MOVE AREA-COD         TO DETAREA-COD
           MOVE AREA-NOME        TO DETAREA-NOME
           MOVE AREA-ESTRUTURA  TO DETAREA-ESTRUTURA

           COMPUTE LIN = CONLIN + 5
           DISPLAY (LIN, 01) DET2
           ADD 1 TO CONLIN
           IF CONLIN < 17
                  GO TO INC-RD2.
      *
       ROT-SOL.
           DISPLAY (22, 15) " *** CONTINUA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (22, 40) W-OPCAO  WITH UPDATE
           IF W-OPCAO = "S"
                  MOVE 01 TO CONLIN
                  DISPLAY TCONSAREA1
                  GO TO INC-RD2.
           IF W-OPCAO NOT = "N"
                  MOVE "*** DIGITE S=SIM   N=NAO ***" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-SOL.
           MOVE 01 TO CONLIN.
           GO TO INC-001.
      *
       NOV-SOL.
           DISPLAY (22, 15) " *** NOVA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (22, 36) W-OPCAO WITH UPDATE
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
           CLOSE CADAREA.
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
           DISPLAY (22, 15) MENS.
       ROT-MENS2.
           ADD 1 TO W-CONT
           IF W-CONT < 1000
              GO TO ROT-MENS2
           ELSE
              DISPLAY (22, 15) LIMPA.
       ROT-MENS-FIM.
           EXIT.
       FIM-ROT-MENS.
      *

      *---------------------*** FIM DE PROGRAMA ***--------------------*
