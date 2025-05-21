       IDENTIFICATION DIVISION.
       PROGRAM-ID. CONSVEN1.
      *AUTHOR. GUSTAVO PEREIRA.
      **************************************
      *       CADASTRO DE VEICULOS         *
      **************************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
             DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       SELECT CADCAR ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS PLACA
                    FILE STATUS  IS ST-ERRO
                    ALTERNATE RECORD KEY IS NOMEPROP WITH DUPLICATES.

      *
      *-----------------------------------------------------------------
       DATA DIVISION.
       FILE SECTION.
       FD CADCAR
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADCAR.DAT".
       01 REGCAR.
          03 PLACA         PIC X(7).
          03 NOMEPROP      PIC X(30).
          03 MARCA         PIC X(2).
          03 ANOFABC       PIC 9(4).
          03 COR           PIC 9(1).
      *
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       77 W-CONT        PIC 9(06) VALUE ZEROS.
       77 W-OPCAO       PIC X(01) VALUE SPACES.
       77 W-ACT         PIC 9(02) VALUE ZEROS.
       77 MENS          PIC X(40) VALUE SPACES.
       77 LIMPA         PIC X(40) VALUE SPACES.
       01 ST-ERRO       PIC X(02) VALUE "00".
       01 W-SEL         PIC 9(01) VALUE ZEROS.
       01 SOLIC         PIC X(20) VALUE SPACES.
       01 CONLIN        PIC 9(03) VALUE 001.
       01 PLACAENTR     PIC X(07) VALUE SPACES.
       01 NOMEPROPENTR  PIC X(30) VALUE SPACES.

       01 DET2.
          03 FILLER                 PIC X(02) VALUE SPACES.
          03 DETPLACA         PIC X(07) VALUE SPACES.
          03 FILLER                 PIC X(02) VALUE SPACES.
          03 DETNOMEPROP      PIC X(30) VALUE SPACES.
          03 FILLER                 PIC X(06) VALUE SPACES.
          03 DETMARCA         PIC X(02) VALUE SPACES.
          03 FILLER                 PIC X(08) VALUE SPACES.
          03 DETANOFABC       PIC 9(04) VALUE ZEROS.
          03 FILLER                 PIC X(09) VALUE SPACES.
          03 DETCOR           PIC 9(01) VALUE ZEROS.

      *-----------------------------------------------------------------
       SCREEN SECTION.
       01  TCONSVEN1.
           05  BLANK SCREEN.
           05  LINE 02  COLUMN 01
               VALUE  "                            *** CONSULTA".
           05  LINE 02  COLUMN 41
               VALUE  " DE VEICULO ***".
           05  LINE 04  COLUMN 01
               VALUE  "    PLACA          NOME PROPRIETARIO".
           05  LINE 04  COLUMN 41
               VALUE  "     MARCA  ANO FABRICACAO   COR".
           05  LINE 22  COLUMN 01
               VALUE  "   MENSAGEM :".
           05  TPLACAENTR
               LINE 06  COLUMN 04  PIC X(07)
               USING  PLACAENTR
               HIGHLIGHT.
           05  TNOMEPROPENTR
               LINE 06  COLUMN 13  PIC X(30)
               USING  NOMEPROPENTR
               HIGHLIGHT.
      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
      *
       INC-OP1.
           OPEN INPUT  CADCAR
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "ARQUIVO DE CADCAR NAO EXISTE" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2
              ELSE
                 MOVE "ERRO ABERTURA DO ARQUIVO CADCAR"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2.
      *
       INC-001.
           MOVE SPACES TO PLACAENTR NOMEPROPENTR
           DISPLAY  TCONSVEN1.
       INC-001A.
           DISPLAY (23, 13)
            "SOLIC.=> F1=ENCERRA CONSULTA   F2=CONSULTA P/PROPRIETARIO."
           ACCEPT TPLACAENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADCAR
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001B.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE PLACAENTR TO PLACA
           START CADCAR KEY IS NOT LESS PLACA INVALID KEY
                 MOVE "*** CHAVE NAO ENCONTRADA ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001.
           GO TO INC-RD2.
       INC-001B.
           DISPLAY (23, 13)
           "SOLIC.=> F1=ENCERRA CONSULTA     F2=CONSULTA P/CODIGO PLACA"
           ACCEPT TNOMEPROPENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADCAR
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001A.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE NOMEPROPENTR TO NOMEPROP
           START CADCAR KEY IS NOT LESS NOMEPROP INVALID KEY
                 MOVE "*** CHAVE NAO ENCONTRADA ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001B.
       INC-RD2.
           DISPLAY (23, 13) LIMPA
           READ CADCAR NEXT
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "10"
                 MOVE "*** FIM DO ARQUIVO  ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO NOV-SOL
              ELSE
                 MOVE "ERRO NA LEITURA DO ARQUIVO CADCAR"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
              NEXT SENTENCE.
       ROT-MONTAR.
           MOVE PLACA         TO DETPLACA
           MOVE NOMEPROP    TO DETNOMEPROP
           MOVE MARCA       TO DETMARCA
           MOVE ANOFABC       TO DETANOFABC
           MOVE COR           TO DETCOR

           COMPUTE LIN = CONLIN + 5
           DISPLAY (LIN, 01) DET2
           ADD 1 TO CONLIN
           IF CONLIN < 17
                  GO TO INC-RD2.
      *
       ROT-SOL.
           DISPLAY (23, 13) " *** CONTINUA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (23, 38) W-OPCAO  WITH UPDATE
           IF W-OPCAO = "S"
                  MOVE 01 TO CONLIN
                  DISPLAY TCONSVEN1
                  GO TO INC-RD2.
           IF W-OPCAO NOT = "N"
                  MOVE "*** DIGITE S=SIM   N=NAO ***" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-SOL.
           MOVE 01 TO CONLIN.
           GO TO INC-001.
      *
       NOV-SOL.
           DISPLAY (23, 13) " *** NOVA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (23, 34) W-OPCAO WITH UPDATE
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
           CLOSE CADCAR.
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
           DISPLAY (23, 13) MENS.
       ROT-MENS2.
           ADD 1 TO W-CONT
           IF W-CONT < 1000
              GO TO ROT-MENS2
           ELSE
              DISPLAY (23, 12) LIMPA.
       ROT-MENS-FIM.
           EXIT.
       FIM-ROT-MENS.
      *

      *---------------------*** FIM DE PROGRAMA ***--------------------*
