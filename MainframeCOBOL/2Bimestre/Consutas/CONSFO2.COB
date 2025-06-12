       IDENTIFICATION DIVISION.
       PROGRAM-ID. CONSCEP.
      ************************************
      * CONSULTA DE FOLHA DE PAGAMENTO 2 *
      ************************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
                    DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
      *
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
      *
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
      *
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       01 W-SEL            PIC 9(01)    VALUE ZEROS.
       01 W-CONT           PIC 9(04)    VALUE ZEROS.
       01 W-OPCAO          PIC X(01)    VALUE SPACES.
       01 ST-ERRO          PIC X(02)    VALUE "00".
       01 W-ACT            PIC 9(02)    VALUE ZEROS.
       01 MENS             PIC X(60)    VALUE SPACES.
       01 LIMPA            PIC X(60)    VALUE SPACES.
       01 SOLIC            PIC X(20)    VALUE SPACES.
       01 CONLIN           PIC 9(03)    VALUE 001.
       01 CODENTR          PIC 9(05)    VALUE ZEROS.
       01 SALBRUTOENTR     PIC 9(06)V99 VALUE ZEROS.
      *-----------------------------------------------------------------
      *
      ******************
      * REPORT SECTION *
      ******************
      *
       01 DET2.
          03 FILLER       PIC X(21)    VALUE "CODIGO FUNCIONARIO : ".
          03 DETCOD       PIC 9(05)    VALUE ZEROS.
          03 FILLER       PIC X(02)    VALUE SPACES.
          03 FILLER       PIC X(16)    VALUE "SALARIO BRUTO : ".
          03 DETSALBRUTO  PIC 9(06)V99 VALUE ZEROS.
          03 FILLER       PIC X(02)    VALUE SPACES.
          03 FILLER       PIC X(06)    VALUE "ANO : ".
          03 DETANO       PIC 9(04)    VALUE ZEROS.
          03 FILLER       PIC X(02)    VALUE SPACES.
          03 FILLER       PIC X(06)    VALUE "MES : ".
          03 DETMES       PIC 9(02)    VALUE ZEROS.
          03 FILLER       PIC X(02)    VALUE SPACES.
       01 DET3.
          03 FILLER       PIC X(07)    VALUE "INSS : ".
          03 DETINSS      PIC 9(06)V99 VALUE ZEROS.
          03 FILLER       PIC X(02)    VALUE SPACES.
          03 FILLER       PIC X(16)    VALUE "IMPOSTO RENDA : ".
          03 DETIR        PIC 9(06)V99 VALUE ZEROS.
          03 FILLER       PIC X(02)    VALUE SPACES.
          03 FILLER       PIC X(18)    VALUE "SALARIO FAMILIA : ".
          03 DETSALFAM    PIC 9(06)V99 VALUE ZEROS.
       01 DET4.
          03 FILLER       PIC X(18)    VALUE "SALARIO LIQUIDO : ".
          03 DETSALLIQ    PIC 9(06)V99 VALUE ZEROS.
      *


      ******************
      * ROTINA DE TELA *
      ******************
      *
       SCREEN SECTION.

       01  TELAFO2.
           05  BLANK SCREEN.
           05  LINE 02  COLUMN 01
               VALUE  "                         *** CONSULTA DE".
           05  LINE 02  COLUMN 41
               VALUE  " FOLHA ***".
           05  LINE 04  COLUMN 01
               VALUE  "   CODIGO :        SALARIO BRUTO :".
           05  LINE 22  COLUMN 01
               VALUE  "  MENSAGEM :".
           05  TCODENTR
               LINE 04  COLUMN 13  PIC 9(05)
               USING  CODENTR
               HIGHLIGHT.
           05  TSALBRUTOENTR
               LINE 04  COLUMN 36  PIC 9(06),99
               USING  SALBRUTOENTR
               HIGHLIGHT.

      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
      *
       INC-OP1.
           OPEN INPUT  CADPAG
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "ARQUIVO DE CADCEP NAO EXISTE" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2
              ELSE
                 MOVE "ERRO ABERTURA DO ARQUIVO CADCEP"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2
           ELSE
               NEXT SENTENCE.
      *
       INC-001.
           MOVE ZEROS TO CODENTR SALBRUTOENTR
           DISPLAY  TELAFO2.
       INC-001A.
           DISPLAY (22, 16)
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
                 MOVE "*** FIM DE PROCESSAMENTO ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001.
           GO TO INC-RD2.
       INC-001B.
           DISPLAY (22, 16)
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
                 MOVE "*** FIM DE PROCESSAMENTO ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001B.
       INC-RD2.
           DISPLAY (22, 16) LIMPA
           READ CADPAG NEXT
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "10"
                 MOVE "*** FIM DE PROCESSAMENTO ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO NOV-SOL
              ELSE
                 MOVE "ERRO NA LEITURA DO ARQUIVO CADPAG"  TO MENS
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
           MOVE SALFAMILIA TO DETSALFAM
           MOVE SALLIQ     TO DETSALLIQ

           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 01) DET2
           ADD 1 TO CONLIN
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 01) DET3
           ADD 1 TO CONLIN
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 01) DET4
           ADD 2 TO CONLIN
           IF CONLIN < 16
                  GO TO INC-RD2.
      *
       ROT-SOL.
           DISPLAY (22, 16) " *** CONTINUA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (22, 41) W-OPCAO  WITH UPDATE
           IF W-OPCAO = "S"
                  MOVE 01 TO CONLIN
                  DISPLAY TELAFO2
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
           DISPLAY (22, 15) MENS.
       ROT-MENS2.
           ADD 1 TO W-CONT
           IF W-CONT < 3000
              GO TO ROT-MENS2
           ELSE
              DISPLAY (22, 15) LIMPA.
       ROT-MENS-FIM.
           EXIT.
       FIM-ROT-MENS.
      *

      *---------------------*** FIM DE PROGRAMA ***--------------------*
