       IDENTIFICATION DIVISION.
       PROGRAM-ID. FPP005.
      * AUTHOR. GUSTAVO PEREIRA.
      **************************************
      * CONSULTA DE FOLHA DE PAGAMENTO 1   *
      **************************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
             DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT CADPAG ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS CHAVEPGTO
                    FILE STATUS  IS ST-ERRO
                    ALTERNATE RECORD KEY IS SALBRUTO WITH DUPLICATES.
      *
      *-----------------------------------------------------------------
       DATA DIVISION.
       FILE SECTION.
       FD CADPAG
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADPAG.DAT".
       01 REGPAG.
          03 CHAVEPGTO.
               05 FUNC-COD PIC 9(5).
               05 ANO      PIC 9(4).
               05 MES      PIC 9(2).
          03 SALBRUTO      PIC 9(06)V99.
          03 INSS          PIC 9(06)V99.
          03 IR            PIC 9(06)V99.
          03 SALFAMILIA    PIC 9(06)V99.
          03 SALLIQ        PIC 9(06)V99.
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       77 W-CONT        PIC 9(06)    VALUE ZEROS.
       77 W-OPCAO       PIC X(01)    VALUE SPACES.
       77 W-ACT         PIC 9(02)    VALUE ZEROS.
       77 MENS          PIC X(60)    VALUE SPACES.
       77 LIMPA         PIC X(60)    VALUE SPACES.
       01 ST-ERRO       PIC X(02)    VALUE "00".
       01 W-SEL         PIC 9(01)    VALUE ZEROS.
       01 IND           PIC 9(02)    VALUE ZEROS.
       01 SOLIC         PIC X(20)    VALUE SPACES.
       01 CONLIN        PIC 9(03)    VALUE 001.
       01 CODENTR       PIC 9(5)     VALUE ZEROS.
       01 SALBRUTOENTR  PIC 9(06)V99 VALUE ZEROS.
      *
      *************************
      * REPORT SECTION *
      *************************
      *
       01 DET2.
          03 FILLER        PIC X(02)    VALUE SPACES.
          03 DETCOD        PIC X(05)    VALUE SPACES.
          03 FILLER        PIC X(02)    VALUE SPACES.
          03 DETANO        PIC X(04)    VALUE SPACES.
          03 FILLER        PIC X(02)    VALUE SPACES.
          03 DETMES        PIC X(02)    VALUE SPACES.
          03 FILLER        PIC X(06)    VALUE SPACES.
          03 DETSALBRUTO   PIC 9(06)V99 VALUE ZEROS.
          03 FILLER        PIC X(04)    VALUE SPACES.
          03 DETINSS       PIC 9(06)V99 VALUE ZEROS.
          03 FILLER        PIC X(02)    VALUE SPACES.
          03 DETIR         PIC 9(06)V99 VALUE ZEROS.
          03 FILLER        PIC X(02)    VALUE SPACES.
          03 DETSALFAMILIA PIC 9(06)V99 VALUE ZEROS.
          03 FILLER        PIC X(04)    VALUE SPACES.
          03 DETSALLIQ     PIC 9(06)V99 VALUE ZEROS.
      *
      ******************
      * ROTINA DE TELA *
      ******************
      *
       SCREEN SECTION.
       01  TFOLHA.
           05  BLANK SCREEN.
           05  LINE 02  COLUMN 01
               VALUE  "                          *** CONSULTA D".
           05  LINE 02  COLUMN 41
               VALUE  "A FOLHA ***".
           05  LINE 04  COLUMN 01
               VALUE  " CODIGO  ANO   MES  SALARIO BRUTO    INS".
           05  LINE 04  COLUMN 41
               VALUE  "S      IR     SAL.FAMILIA  SAL.LIQUIDO".
           05  LINE 22  COLUMN 01
               VALUE  "  MENSAGEM :".
           05  TCODENTR
               LINE 06  COLUMN 02  PIC X(05)
               USING  CODENTR
               HIGHLIGHT.
           05  TSALBRUTOENTR
               LINE 06  COLUMN 22  PIC 9(06),99
               USING  SALBRUTOENTR
               HIGHLIGHT.

      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
      *
       INC-OP1.
           OPEN INPUT CADPAG
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "ARQUIVO DE CADPAG NAO EXISTE" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2
              ELSE
                 MOVE "ERRO ABERTURA DO ARQUIVO CADPAG"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2.
      *
       INC-001.
           MOVE ZEROS TO CODENTR SALBRUTOENTR
           DISPLAY TFOLHA.
       INC-001A.
           DISPLAY (22, 13)
            "SOLIC.=> F1=ENCERRA CONSULTA   F2=CONSULTA P/SAL. BRUTO"
           ACCEPT TCODENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADPAG
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001B.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE CODENTR TO FUNC-COD
           START CADPAG KEY IS NOT LESS FUNC-COD INVALID KEY
                 MOVE "*** CHAVE NAO ENCONTRADA ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001.
           GO TO INC-RD2.
       INC-001B.
           DISPLAY (22, 15)
            "SOLIC.=> F1=ENCERRA CONSULTA     F2=CONSULTA P/CODIGO FUNC"
           ACCEPT TSALBRUTOENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADPAG
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001A.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE SALBRUTOENTR TO SALBRUTO
           START CADPAG KEY IS NOT LESS SALBRUTO INVALID KEY
                 MOVE "*** CHAVE NAO ENCONTRADA ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001B.
       INC-RD2.
           DISPLAY (22, 15) LIMPA
           READ CADPAG NEXT
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
           MOVE FUNC-COD   TO DETCOD
           MOVE ANO        TO DETANO
           MOVE MES        TO DETMES
           MOVE SALBRUTO   TO DETSALBRUTO
           MOVE INSS       TO DETINSS
           MOVE IR         TO DETIR
           MOVE SALFAMILIA TO DETSALFAMILIA
           MOVE SALLIQ     TO DETSALLIQ

           COMPUTE LIN = CONLIN + 5
           DISPLAY (LIN, 01) DET2
           ADD 1 TO CONLIN
           IF CONLIN < 17
                  GO TO INC-RD2.
      *
       ROT-SOL.
           DISPLAY (22, 16) " *** CONTINUA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (22, 40) W-OPCAO  WITH UPDATE
           IF W-OPCAO = "S"
                  MOVE 01 TO CONLIN
                  DISPLAY TFOLHA
                  GO TO INC-RD2.
           IF W-OPCAO NOT = "N"
                  MOVE "*** DIGITE S=SIM   N=NAO ***" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-SOL.
           MOVE 01 TO CONLIN.
           GO TO INC-001.
      *
       NOV-SOL.
           DISPLAY (22, 16) " *** NOVA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (22, 37) W-OPCAO WITH UPDATE
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
           CLOSE CADPAG.
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
