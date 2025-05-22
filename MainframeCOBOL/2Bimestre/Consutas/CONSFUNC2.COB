       IDENTIFICATION DIVISION.
       PROGRAM-ID. PROGRAMA03.
      *AUTHOR. GUSTAVO PEREIRA.
      **************************************
      * MANUTENCAO DO CADASTRO DE AMIGOS   *
      **************************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
             DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       SELECT CADFUNC ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS CODFUNC
                    FILE STATUS  IS ST-ERRO
                    ALTERNATE RECORD KEY IS NOME WITH DUPLICATES.

      *
      *-----------------------------------------------------------------
       DATA DIVISION.
       FILE SECTION.
       FD CADFUNC
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADFUNC.DAT".
       01 REGFUNC.
          03 CODFUNC       PIC 9(5).
          03 NOME          PIC X(35).
          03 CODCARGO      PIC 9(3).
          03 NDEPS         PIC 9(2).
          03 CODDEP        PIC 9(3).
          03 CODAREA       PIC 9(3).
          03 ENDERECO      PIC 9(8).
          03 NENDRC        PIC 9(5).
          03 COMPENDRC     PIC X(10).
          03 EMAIL         PIC X(30).
          03 TELEFONE.
             05 DDD        PIC 9(2).
             05 NUMERO     PIC 9(9).
          03 SITFUNC       PIC X(1).
          03 DTADMISSAO    PIC 9(8).
          03 DTDEMISSAO    PIC 9(8).
          03 DTAFAST       PIC 9(8).
      *
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       77 W-CONT        PIC 9(06) VALUE ZEROS.
       77 W-OPCAO       PIC X(01) VALUE SPACES.
       77 W-ACT         PIC 9(02) VALUE ZEROS.
       77 MENS          PIC X(50) VALUE SPACES.
       77 LIMPA         PIC X(50) VALUE SPACES.
       01 ST-ERRO       PIC X(02) VALUE "00".
       01 W-SEL            PIC 9(01) VALUE ZEROS.
       01 IND              PIC 9(02) VALUE ZEROS.
       01 SOLIC            PIC X(20) VALUE SPACES.
       01 CONLIN           PIC 9(03) VALUE 001.
       01 CODFUNCENTR      PIC 9(05) VALUE ZEROS.
       01 NOMEENTR           PIC X(35) VALUE SPACES.
      *-----------------------------------------------------------------
      *
      ******************
      * REPORT SECTION *
      ******************
      *
       01 DET2.
          03 FILLER         PIC X(14) VALUE "CODIGO FUNC : ".
          03 DETCODFUNC     PIC 9(06).
          03 FILLER         PIC X(02) VALUE SPACES.
          03 FILLER         PIC X(07) VALUE "NOME : ".
          03 DETNOME        PIC X(36).
       01 DET3.
          03 FILLER         PIC X(21) VALUE "NUMERO DEPENDENTES : ".
          03 DETNDEPS       PIC 9(3).
          03 FILLER         PIC X(02) VALUE SPACES.
          03 FILLER         PIC X(15) VALUE "CODIGO CARGO : ".
          03 DETCODCARGO    PIC 9(03).
       01 DET4.
          03 FILLER         PIC X(06) VALUE "CEP : ".
          03 DETENDERECO    PIC 9(09).
          03 FILLER         PIC X(18) VALUE "NUMERO ENDERECO : ".
          03 FILLER    PIC X(02) VALUE SPACES.
          03 DETNENDRC      PIC 9(06).
          03 FILLER    PIC X(02) VALUE SPACES.
          03 FILLER         PIC X(23) VALUE "COMPLEMENTO ENDERECO : ".
          03 DETCOMPENDRC   PIC X(11).
       01 DET5.
          03 FILLER         PIC X(08) VALUE "EMAIL : ".
          03 DETEMAIL       PIC X(31).
          03 FILLER    PIC X(02) VALUE SPACES.
          03 FILLER         PIC X(13) VALUE "TELEFONE : ".
          03 DETTELEFONE    PIC 99.99999.9999.
       01 DET6.
          03 FILLER         PIC X(23) VALUE "SITUACAO FUNCIONARIO : ".
          03 DETSITFUNC     PIC X(2).
       01 DET7.
          03 FILLER         PIC X(16) VALUE "DATA ADMISSAO : ".
          03 DETDTADMISSAO  PIC X(08).
          03 FILLER    PIC X(02) VALUE SPACES.
          03 FILLER         PIC X(16) VALUE "DATA DEMISSAO : ".
          03 DETDTDEMISSAO  PIC X(08).
          03 FILLER    PIC X(02) VALUE SPACES.
          03 FILLER         PIC X(19) VALUE "DATA AFASTAMENTO : ".
          03 DETDTAFAST     PIC X(08).
      *-----------------------------------------------------------------
       SCREEN SECTION.
       01  TCONSFUNC.
           05  BLANK SCREEN.
           05  LINE 02  COLUMN 01
               VALUE  "                        *** CONSULTA DE".
           05  LINE 02  COLUMN 41
               VALUE  "FUNCIONARIO ***".
           05  LINE 04  COLUMN 01
               VALUE  "   CODIGO FUNCIONARIO :       NOME :".
           05  LINE 22  COLUMN 01
               VALUE  "   MENSAGEM :".
           05  TCODFUNCENTR
               LINE 04  COLUMN 25  PIC 9(05)
               USING  CODFUNCENTR
               HIGHLIGHT.
           05  TNOMEENTR
               LINE 04  COLUMN 38  PIC X(35)
               USING  NOMEENTR
               HIGHLIGHT.


      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
      *
       INC-OP1.
           OPEN INPUT  CADFUNC
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "ARQUIVO DE CADFUNC NAO EXISTE" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2
              ELSE
                 MOVE "ERRO ABERTURA DO ARQUIVO CADFUNC"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2
           ELSE
               NEXT SENTENCE.
      *
       INC-001.
           MOVE ZEROS TO CODFUNCENTR
           MOVE SPACES TO NOMEENTR
           DISPLAY  TCONSFUNC.
       INC-001A.
           DISPLAY (23, 13)
            "SOLIC.=> F1=ENCERRA CONSULTA   F2=CONSULTA P/NOME."
           ACCEPT TCODFUNCENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADFUNC
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001B.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE CODFUNCENTR TO CODFUNC
           START CADFUNC KEY IS NOT LESS CODFUNC INVALID KEY
                 MOVE "*** FIM DE PROCESSAMENTO ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001.
           GO TO INC-RD2.
       INC-001B.
           DISPLAY (23, 13)
           "SOLIC.=> F1=ENCERRA CONSULTA   F2=CONSULTA P/CODIGO FUNC"

           ACCEPT TNOMEENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADFUNC
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001A.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE NOMEENTR TO NOME
           START CADFUNC KEY IS NOT LESS NOME INVALID KEY
                 MOVE "*** FIM DE PROCESSAMENTO ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001B.
       INC-RD2.
           DISPLAY (23, 13) LIMPA
           READ CADFUNC NEXT
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "10"
                 MOVE "*** FIM DE PROCESSAMENTO ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO NOV-SOL
              ELSE
                 MOVE "ERRO NA LEITURA DO ARQUIVO CADFUNC"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
              NEXT SENTENCE.
       ROT-MONTAR.
           MOVE CODFUNC TO DETCODFUNC
           MOVE NOME TO DETNOME
           MOVE NDEPS TO DETNDEPS
           MOVE CODCARGO TO DETCODCARGO
           MOVE ENDERECO TO DETENDERECO
           MOVE NENDRC TO DETNENDRC
           MOVE COMPENDRC TO DETCOMPENDRC
           MOVE EMAIL TO DETEMAIL
           MOVE TELEFONE TO DETTELEFONE
           MOVE SITFUNC TO DETSITFUNC
           MOVE DTADMISSAO TO DETDTADMISSAO
           MOVE DTDEMISSAO TO DETDTDEMISSAO
           MOVE DTAFAST    TO DETDTAFAST

           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 02) DET2
           ADD 1 TO CONLIN
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 02) DET3
           ADD 1 TO CONLIN
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 02) DET4
           ADD 1 TO CONLIN
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 02) DET5
           ADD 1 TO CONLIN
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 02) DET6
           ADD 1 TO CONLIN
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 02) DET7
           ADD 2 TO CONLIN
           IF CONLIN < 15
                  GO TO INC-RD2.

      *
       ROT-SOL.
           DISPLAY (23, 13) " *** CONTINUA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (23, 38) W-OPCAO  WITH UPDATE
           IF W-OPCAO = "S"
                  MOVE 01 TO CONLIN
                  DISPLAY TCONSFUNC
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
           CLOSE CADFUNC.
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
           IF W-CONT < 3000
              GO TO ROT-MENS2
           ELSE
              DISPLAY (23, 12) LIMPA.
       ROT-MENS-FIM.
           EXIT.
       FIM-ROT-MENS.
      *

      *---------------------*** FIM DE PROGRAMA ***--------------------*
