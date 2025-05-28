       IDENTIFICATION DIVISION.
       PROGRAM-ID. PROG03.
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
           SELECT CADPAG ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS CHAVEPGTO
                    FILE STATUS  IS ST-ERRO
                    ALTERNATE RECORD KEY IS SALBRUTO WITH DUPLICATES.
      *
           SELECT CADFUNC ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS CODFUNC
                    FILE STATUS  IS ST-ERRO.
      *
           SELECT CADCARGO ASSIGN TO DISK
                      ORGANIZATION IS INDEXED
                      ACCESS MODE  IS DYNAMIC
                      RECORD KEY   IS CARGO-COD
                      ALTERNATE RECORD KEY IS CARGO-DNMNC
                                   WITH DUPLICATES
                      FILE STATUS  IS ST-ERRO.
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
      *
      *-----------------------------------------------------------------
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
       FD CADCARGO
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "ARQCARGO.DAT".
       01 REGCARGO.
               03 CARGO-COD        PIC 9(5).
               03 CARGO-DNMNC      PIC X(20).
               03 CARGO-AREA       PIC 9(3).
               03 CARGO-TPSAL      PIC 9(1).
               03 CARGO-SALBASE    PIC 9(6)V99.
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       77 W-CONT        PIC 9(06)   VALUE ZEROS.
       77 W-OPCAO       PIC X(01)   VALUE SPACES.
       77 W-ACT         PIC 9(02)   VALUE ZEROS.
       77 MENS          PIC X(50)   VALUE SPACES.
       77 LIMPA         PIC X(50)   VALUE SPACES.
       01 ST-ERRO       PIC X(02)   VALUE "00".
       01 W-SEL         PIC 9(01)   VALUE ZEROS.
       01 IND           PIC 9(02)   VALUE ZEROS.
       01 X             PIC 9(03)   VALUE ZEROS.
       01 Y             PIC 9(02)   VALUE ZEROS.
       01 Z             PIC 9(02)   VALUE ZEROS.
       01 CALC          PIC 9(6)V99 VALUE ZEROS.
       01 DESCINSS      PIC 9(6)V99 VALUE ZEROS.
      *-----------------------------------------------------------------
       SCREEN SECTION.
       01  TELAPAG.
           05  BLANK SCREEN.
           05  LINE 01  COLUMN 01
               VALUE  "                       ฺฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤ".
           05  LINE 01  COLUMN 41
               VALUE  "ฤฤฤฤฟ".
           05  LINE 02  COLUMN 01
               VALUE  "                       ณ  CADASTRO DE FO".
           05  LINE 02  COLUMN 41
               VALUE  "LHA ณ".
           05  LINE 03  COLUMN 01
               VALUE  "                       ภฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤ".
           05  LINE 03  COLUMN 41
               VALUE  "ฤฤฤฤู".
           05  LINE 04  COLUMN 01
               VALUE  "ษอออออออออออออออออออออออออออออออออออออออ".
           05  LINE 04  COLUMN 41
               VALUE  "อออออออออออออออออออออออออออออออออออออออป".
           05  LINE 05  COLUMN 01
               VALUE  "บ                             ฺฤฤฤฤฤฤฤฤฤ".
           05  LINE 05  COLUMN 41
               VALUE  "ฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฟ  บ".
           05  LINE 06  COLUMN 01
               VALUE  "บ  CODIGO FUNCIONARIO :       ณ    ** RE".
           05  LINE 06  COLUMN 41
               VALUE  "SULTADO DA FOLHA DE PAGAMENTO **    ณ  บ".
           05  LINE 07  COLUMN 01
               VALUE  "บ                             ณ  SALARIO".
           05  LINE 07  COLUMN 41
               VALUE  " BRUTO    :                         ณ  บ".
           05  LINE 08  COLUMN 01
               VALUE  "บ                             ณ".
           05  LINE 08  COLUMN 41
               VALUE  "                                    ณ  บ".
           05  LINE 09  COLUMN 01
               VALUE  "บ                             ณ".
           05  LINE 09  COLUMN 41
               VALUE  "                                    ณ  บ".
           05  LINE 10  COLUMN 01
               VALUE  "บ                             ณ  INSS".
           05  LINE 10  COLUMN 41
               VALUE  "          :                         ณ  บ".
           05  LINE 11  COLUMN 01
               VALUE  "บ                             ณ".
           05  LINE 11  COLUMN 41
               VALUE  "                                    ณ  บ".
           05  LINE 12  COLUMN 01
               VALUE  "บ  ANO                :       ณ  IMPOSTO".
           05  LINE 12  COLUMN 41
               VALUE  " DE RENDA :                         ณ  บ".
           05  LINE 13  COLUMN 01
               VALUE  "บ                             ณ".
           05  LINE 13  COLUMN 41
               VALUE  "                                    ณ  บ".
           05  LINE 14  COLUMN 01
               VALUE  "บ                             ณ".
           05  LINE 14  COLUMN 41
               VALUE  "                                    ณ  บ".
           05  LINE 15  COLUMN 01
               VALUE  "บ                             ณ  SALARIO".
           05  LINE 15  COLUMN 41
               VALUE  " FAMILIA  :                         ณ  บ".
           05  LINE 16  COLUMN 01
               VALUE  "บ                             ณ".
           05  LINE 16  COLUMN 41
               VALUE  "                                    ณ  บ".
           05  LINE 17  COLUMN 01
               VALUE  "บ                             ณ  SALARIO".
           05  LINE 17  COLUMN 41
               VALUE  " LIQUIDO  :                         ณ  บ".
           05  LINE 18  COLUMN 01
               VALUE  "บ                             ณ".
           05  LINE 18  COLUMN 41
               VALUE  "                                    ณ  บ".
           05  LINE 19  COLUMN 01
               VALUE  "บ  MES                :       ภฤฤฤฤฤฤฤฤฤ".
           05  LINE 19  COLUMN 41
               VALUE  "ฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤู  บ".
           05  LINE 20  COLUMN 01
               VALUE  "บ".
           05  LINE 20  COLUMN 41
               VALUE  "                                       บ".
           05  LINE 21  COLUMN 01
               VALUE  "ศอออออออออออออออออออออออออออออออออออออออ".
           05  LINE 21  COLUMN 41
               VALUE  "อออออออออออออออออออออออออออออออออออออออผ".
           05  LINE 22  COLUMN 01
               VALUE  "ฺฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤ".
           05  LINE 22  COLUMN 41
               VALUE  "ฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฟ".
           05  LINE 23  COLUMN 01
               VALUE  "ณMENSAGEM :".
           05  LINE 23  COLUMN 41
               VALUE  "                                       ณ".
           05  LINE 24  COLUMN 01
               VALUE  "ภฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤ".
           05  LINE 24  COLUMN 41
               VALUE  "ฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤฤู".
           05  TFUNC-COD
               LINE 06  COLUMN 25  PIC 9(05)
               USING  FUNC-COD
               HIGHLIGHT.
           05  TANO
               LINE 12  COLUMN 25  PIC 9(04)
               USING  ANO
               HIGHLIGHT.
           05  TMES
               LINE 19  COLUMN 25  PIC 9(02)
               USING  MES
               HIGHLIGHT.
           05  TSALBRUTO
               LINE 07  COLUMN 54  PIC 9(06),99
               USING  SALBRUTO
               HIGHLIGHT.
           05  TINSS
               LINE 10  COLUMN 54  PIC 9(06),99
               USING  INSS
               HIGHLIGHT.
           05  TIR
               LINE 12  COLUMN 54  PIC 9(06),99
               USING  IR
               HIGHLIGHT.
           05  TSALFAMILIA
               LINE 15  COLUMN 54  PIC 9(06),99
               USING  SALFAMILIA
               HIGHLIGHT.
           05  TSALLIQ
               LINE 17  COLUMN 54  PIC 9(06),99
               USING  SALLIQ
               HIGHLIGHT.
      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
      *
       INC-OP1.
           OPEN I-O CADPAG
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 OPEN OUTPUT CADPAG
                 CLOSE CADPAG
                 MOVE "*** ARQUIVO CAFUNC FOI CRIADO **" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-OP1
              ELSE
                 MOVE "ERRO NA ABERTURA DO ARQUIVO CADFUNC" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM.

       INC-OP2.
           OPEN INPUT  CADFUNC
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "ARQUIVO DE CADCEP NAO EXISTE" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
              ELSE
                 MOVE "ERRO ABERTURA DO ARQUIVO CADCEP"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM.
       INC-OP3.
           OPEN INPUT  CADCARGO
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "ARQUIVO DE CADCEP NAO EXISTE" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
              ELSE
                 MOVE "ERRO ABERTURA DO ARQUIVO CADCEP"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM.
      *
       R1.
           MOVE ZEROS TO CODFUNC CODCARGO CODDEP NDEPS CODAREA
           MOVE ZEROS TO ENDERECO NENDRC DDD NUMERO W-SEL CARGO-SALBASE
           MOVE ZEROS TO DTADMISSAO DTDEMISSAO DTAFAST
           MOVE ZEROS TO SALBRUTO INSS IR SALFAMILIA SALLIQ
           DISPLAY TELAPAG.
       R2.
           ACCEPT TFUNC-COD
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO ROT-FIM.
       R2A.
           MOVE FUNC-COD TO CODFUNC.
           READ CADFUNC
           IF ST-ERRO NOT = "00"
               IF ST-ERRO = "23"
                   MOVE "*** FUNC DIGITAD NAO ENCONTRADA ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R2
               ELSE
                  MOVE "ERRO NA LEITURA ARQUIVO CADASTRO FUNC" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-FIM
           ELSE
                DISPLAY TELAPAG.

           IF SITFUNC IS NOT = "A"
               MOVE "O FUNCIONARIO NAO ESTA ATIVO NA EMPRESA" TO MENS
               PERFORM ROT-MENS THRU ROT-MENS-FIM
               GO TO R2.
       R3.
           ACCEPT TANO
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO ROT-FIM.
       R4.
           ACCEPT TMES
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO ROT-FIM.

       LER-CADPAG.
           READ CADPAG
           IF ST-ERRO NOT = "23"
             IF ST-ERRO = "00"
                DISPLAY TELAPAG
                MOVE "*** FOLHA JA CADASTRADA ***" TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ACE-001
             ELSE
                MOVE "ERRO NA LEITURA ARQUIVO CADPAG" TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM
           ELSE
                NEXT SENTENCE.
       R5.
           MOVE CODCARGO TO CARGO-COD
           READ CADCARGO
           IF ST-ERRO NOT = "00"
               IF ST-ERRO = "23"
                   MOVE "*** CARGO DIGITADO NAO ENCONTRADO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R2
               ELSE
                  MOVE "ERRO NA LEITURA ARQUIVO CADASTRO CARGO" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-FIM
           ELSE
                DISPLAY TELAPAG.
       R5A.
           IF CARGO-TPSAL = 1
               MOVE 220 TO X
           ELSE IF CARGO-TPSAL = 2
               MOVE 30 TO X
           ELSE
               MOVE 1 TO X.
           COMPUTE SALBRUTO = (CARGO-SALBASE * X).
       R5B.
           IF SALBRUTO < 4000,01
               MOVE 8 TO Y
           ELSE IF SALBRUTO > 10000
               MOVE 10 TO Y
           ELSE
               MOVE 9 TO Y.
           COMPUTE SALBRUTO = (SALBRUTO * Y).
       RSC.
           COMPUTE DESCINSS = (SALBRUTO - INSS).
           IF DESCINSS < 6000,01
               MOVE 0 TO Z
           ELSE IF DESCINSS > 6000,00 AND DESCINSS < 1500,01
               MOVE 10 TO Z
           ELSE
               MOVE 15 TO Z
           COMPUTE IR = DESCINSS * Z.
       R5D.
           COMPUTE SALFAMILIA = NDEPS * 10
           COMPUTE SALLIQ = DESCINSS - (IR + SALFAMILIA)

           MOVE "CRIACAO DA FOLHA DE PAGAMENTO CONCLUIDA" TO MENS
           PERFORM ROT-MENS THRU ROT-MENS-FIM
           GO TO ROT-FIM.
       INC-OPC.
                MOVE "S" TO W-OPCAO
                DISPLAY (23, 40) "DADOS OK (S/N) : "
                ACCEPT (23, 57) W-OPCAO WITH UPDATE
                ACCEPT W-ACT FROM ESCAPE KEY
                IF W-ACT = 01 GO TO R5
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** DADOS RECUSADOS PELO OPERADOR ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "*** DIGITE APENAS S=SIM e N=NAO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-OPC.
       INC-WR1.
                WRITE REGPAG
                IF ST-ERRO = "00" OR "02"
                      MOVE "*** DADOS GRAVADOS *** " TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO R1
                IF ST-ERRO = "22"
                  MOVE "* PAG JA EXISTE,DADOS NAO GRAVADOS *" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO R1
                ELSE
                      MOVE "ERRO NA GRAVACAO DO ARQUIVO DE FUNCIONARIO"
                               TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO ROT-FIM.
      *
      *****************************************
      * ROTINA DE CONSULTA/ALTERACAO/EXCLUSAO *
      *****************************************
      *
       ACE-001.
                DISPLAY (23, 12)
                     "N=NOVO REGISTRO   A=ALTERAR   E=EXCLUIR"
                ACCEPT (23, 55) W-OPCAO
                IF W-OPCAO NOT = "N" AND W-OPCAO NOT = "A"
                    AND W-OPCAO NOT = "E" GO TO ACE-001.
                MOVE SPACES TO MENS
                DISPLAY (23, 12) MENS
                IF W-OPCAO = "N"
                   GO TO R1
                ELSE
                   IF W-OPCAO = "A"
                      MOVE 1 TO W-SEL
                      GO TO R3.
      *
       EXC-OPC.
                DISPLAY (23, 40) "EXCLUIR   (S/N) : ".
                ACCEPT (23, 57) W-OPCAO
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** REGISTRO NAO EXCLUIDO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "* DIGITE APENAS S=SIM  e  N=NAO *" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO EXC-OPC.
       EXC-DL1.
                DELETE CADPAG RECORD
                IF ST-ERRO = "00"
                   MOVE "*** REGISTRO AMIGO EXCLUIDO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                MOVE "ERRO NA EXCLUSAO DO REGISTRO "   TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM.
      *
       ALT-OPC.
                DISPLAY (23, 40) "ALTERAR  (S/N) : ".
                ACCEPT (23, 57) W-OPCAO
                ACCEPT W-ACT FROM ESCAPE KEY
                IF W-ACT = 01 GO TO R5.
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** INFORMACOES NAO ALTERADAS *** " TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "*** DIGITE APENAS S=SIM  e  N=NAO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO ALT-OPC.
       ALT-RW1.
                REWRITE REGPAG
                IF ST-ERRO = "00" OR "02"
                   MOVE "*** REGISTRO ALTERADO ***         " TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                MOVE "ERRO NA EXCLUSAO DO REGISTRO FUNC"   TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM.
      *-------------------------------------------------------------------------------------------
       ROT-FIM.
           CLOSE CADPAG CADFUNC CADCARGO
           STOP RUN.

      *---------[ ROTINA DE MENSAGEM ]---------------------
       ROT-MENS.
                MOVE ZEROS TO W-CONT.
       ROT-MENS1.
               DISPLAY (22, 13) MENS.
       ROT-MENS2.
                ADD 1 TO W-CONT
                IF W-CONT < 3000
                   GO TO ROT-MENS2
                ELSE
                   MOVE SPACES TO MENS
                   DISPLAY (22, 13) MENS.
       ROT-MENS-FIM.
                EXIT.
       FIM-ROT-TEMPO.

      *    FILE STATUS
      *    00 = OPERA  O REALIZADO COM SUCESSO
      *    22 = REGISTRO J  CADASTRADO
      *    23 = REGISTRO N O ENCONTRADO
      *    30 = ARQUIVO N O ENCONTRADO
