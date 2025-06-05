       IDENTIFICATION DIVISION.
       PROGRAM-ID. FPP003.
      * AUTHOR. GUSTAVO PEREIRA.
      ********************************************************
      * MANUTENCAO DO CADASTRO DE CARGOS   *
      ********************************************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
             DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT CADCARGO ASSIGN TO DISK
                      ORGANIZATION IS INDEXED
                      ACCESS MODE  IS DYNAMIC
                      RECORD KEY   IS CARGO-COD
                      ALTERNATE RECORD KEY IS CARGO-DNMNC
                                   WITH DUPLICATES
                      FILE STATUS  IS ST-ERRO.
      *
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
       FD CADCARGO
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "ARQCARGO.DAT".
       01 REGCARGO.
               03 CARGO-COD        PIC 9(5).
               03 CARGO-DNMNC      PIC X(20).
               03 CARGO-AREA       PIC 9(3).
               03 CARGO-TPSAL      PIC 9(1).
               03 CARGO-SALBASE    PIC 9(6)V99.
      *
      *-----------------------------------------------------------------
       FD CADAREA
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADAREA.DAT".
       01 REGAREA.
                03 AREA-COD          PIC 9(03).
                03 AREA-NOME         PIC X(20).
                03 AREA-ESTRUTURA    PIC 9(1).
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       77 W-CONT               PIC 9(06) VALUE ZEROS.
       77 W-OPCAO              PIC X(01) VALUE SPACES.
       77 W-ACT                PIC 9(02) VALUE ZEROS.
       77 MENS                 PIC X(50) VALUE SPACES.
       77 LIMPA                PIC X(50) VALUE SPACES.
       01 ST-ERRO              PIC X(02) VALUE "00".
       01 W-SEL                PIC 9(01) VALUE ZEROS.
       01 IND                  PIC 9(02) VALUE ZEROS.
       01 TXTCARGO-TPSAL       PIC X(10) VALUE SPACES.
       01 TXTAREA-ESTRUTURA    PIC X(20) VALUE SPACES.

       01 TABTPSAL.
          03 TBTPSAL   PIC X(10) OCCURS 3 TIMES.

      *-----------------------------------------------------------------
       SCREEN SECTION.

       01  TELACARGO.
           05  BLANK SCREEN.
           05  LINE 01  COLUMN 01
               VALUE  "                             ÚÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 01  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄ¿".
           05  LINE 02  COLUMN 01
               VALUE  "                             ³  CADASTRO".
           05  LINE 02  COLUMN 41
               VALUE  " CARGO  ³".
           05  LINE 03  COLUMN 01
               VALUE  "                             ÀÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 03  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÙ".
           05  LINE 04  COLUMN 01
               VALUE  " ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ".
           05  LINE 04  COLUMN 41
               VALUE  "ÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ»".
           05  LINE 05  COLUMN 01
               VALUE  " º".
           05  LINE 05  COLUMN 41
               VALUE  " ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿º".
           05  LINE 06  COLUMN 01
               VALUE  " º  CODIGO CARGO :".
           05  LINE 06  COLUMN 41
               VALUE  " ³            DADOS AREA              ³º".
           05  LINE 07  COLUMN 01
               VALUE  " º".
           05  LINE 07  COLUMN 41
               VALUE  " ³                                    ³º".
           05  LINE 08  COLUMN 01
               VALUE  " º".
           05  LINE 08  COLUMN 41
               VALUE  " ³                                    ³º".
           05  LINE 09  COLUMN 01
               VALUE  " º  DENOMINACAO  :".
           05  LINE 09  COLUMN 41
               VALUE  " ³                                    ³º".
           05  LINE 10  COLUMN 01
               VALUE  " º".
           05  LINE 10  COLUMN 41
               VALUE  " ³                                    ³º".
           05  LINE 11  COLUMN 01
               VALUE  " º".
           05  LINE 11  COLUMN 41
               VALUE  " ³  NOME AREA :                       ³º".
           05  LINE 12  COLUMN 01
               VALUE  " º  AREA CARGO   :".
           05  LINE 12  COLUMN 41
               VALUE  " ³                                    ³º".
           05  LINE 13  COLUMN 01
               VALUE  " º".
           05  LINE 13  COLUMN 41
               VALUE  " ³                                    ³º".
           05  LINE 14  COLUMN 01
               VALUE  " º".
           05  LINE 14  COLUMN 41
               VALUE  " ³                                    ³º".
           05  LINE 15  COLUMN 01
               VALUE  " º  TIPO SALARIO :".
           05  LINE 15  COLUMN 41
               VALUE  " ³                                    ³º".
           05  LINE 16  COLUMN 01
               VALUE  " º".
           05  LINE 16  COLUMN 41
               VALUE  " ³  ESTRUTURA :                       ³º".
           05  LINE 17  COLUMN 01
               VALUE  " º  SALARIO BASE :".
           05  LINE 17  COLUMN 41
               VALUE  " ³                                    ³º".
           05  LINE 18  COLUMN 01
               VALUE  " º".
           05  LINE 18  COLUMN 41
               VALUE  " ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙº".
           05  LINE 19  COLUMN 01
               VALUE  " ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ".
           05  LINE 19  COLUMN 41
               VALUE  "ÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ¼".
           05  LINE 20  COLUMN 01
               VALUE  " ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 20  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿".
           05  LINE 21  COLUMN 01
               VALUE  " ³ MENSAGEM :".
           05  LINE 21  COLUMN 41
               VALUE  "                                       ³".
           05  LINE 22  COLUMN 01
               VALUE  " ³".
           05  LINE 22  COLUMN 41
               VALUE  "                                       ³".
           05  LINE 23  COLUMN 01
               VALUE  " ³".
           05  LINE 23  COLUMN 41
               VALUE  "                                       ³".
           05  LINE 24  COLUMN 01
               VALUE  " ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 24  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ".
           05  TCARGO-COD
               LINE 06  COLUMN 20  PIC 9(05)
               USING  CARGO-COD
               HIGHLIGHT.
           05  TCARGO-DNMNC
               LINE 09  COLUMN 20  PIC X(20)
               USING  CARGO-DNMNC
               HIGHLIGHT.
           05  TAREA-NOME
               LINE 11  COLUMN 57  PIC X(20)
               USING  AREA-NOME
               HIGHLIGHT.
           05  TCARGO-AREA
               LINE 12  COLUMN 20  PIC X(03)
               USING  CARGO-AREA
               HIGHLIGHT.
           05  TCARGO-TPSAL
               LINE 15  COLUMN 20  PIC 9(1)
               USING  CARGO-TPSAL
               HIGHLIGHT.
           05  TTXTCARGO-TPSAL
               LINE 15  COLUMN 22  PIC X(10)
               USING  TXTCARGO-TPSAL
               HIGHLIGHT.
           05  TAREA-ESTRUTURA
               LINE 16  COLUMN 57  PIC 9(01)
               USING  AREA-ESTRUTURA
               HIGHLIGHT.
           05  TTXTAREA-ESTRUTURA
               LINE 16  COLUMN 59  PIC X(20)
               USING  TXTAREA-ESTRUTURA
               HIGHLIGHT.
           05  TCARGO-SALBASE
               LINE 17  COLUMN 20  PIC 9(06),99
               USING  CARGO-SALBASE
               HIGHLIGHT.

      *
       01  TELATS.
           05  LINE 21  COLUMN 38 VALUE  "1-HORISTA".
           05  LINE 22  COLUMN 38 VALUE  "2-DIARISTA".
           05  LINE 23  COLUMN 38 VALUE  "3-MENSALISTA".
      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
           MOVE "HORISTA"          TO TBTPSAL(1).
           MOVE "DIARISTA"         TO TBTPSAL(2).
           MOVE "MENSALISTA"       TO TBTPSAL(3).
      *----------------------[ ROTINAS DE ABERTURA DE ARQUIVOS ]-------
       R0.
           OPEN I-O CADCARGO
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 OPEN OUTPUT CADCARGO
                 CLOSE CADCARGO
                 MOVE "*** ARQUIVO CADCARGO FOI CRIADO **" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R0
              ELSE
                 MOVE "ERRO NA ABERTURA DO ARQUIVO CADCARGO" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
                 NEXT SENTENCE.
      *
       R0A.
           OPEN I-O CADAREA
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "*** ARQUIVO DE AREA NAO ENCONTRADO **" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
              ELSE
                 MOVE "ERRO NA ABERTURA DO ARQUIVO DE AREA " TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
                 NEXT SENTENCE.
      *------------[ INICIALIZACAO DAS VARIAVEIS ]---------------------
       R1.
           MOVE SPACES TO CARGO-DNMNC AREA-NOME
           MOVE SPACES TO TXTCARGO-TPSAL TXTAREA-ESTRUTURA
           MOVE ZEROS TO CARGO-COD CARGO-AREA CARGO-TPSAL CARGO-SALBASE
           MOVE ZEROS TO AREA-COD AREA-ESTRUTURA
      *-------------[VISUALIZACAO DA TELA]--------------------------------
           DISPLAY TELACARGO.
       R2.
           ACCEPT TCARGO-COD

           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02 GO TO ROT-FIM.

           IF CARGO-COD = ZEROS
               MOVE "*** CODIGO INVALIDO  ***" TO MENS
               PERFORM ROT-MENS THRU ROT-MENS-FIM
               GO TO R1.



       LER-CADMCARGO.
           READ CADCARGO
           IF ST-ERRO NOT = "23"
              IF ST-ERRO = "00"
                PERFORM R4A
                PERFORM R5A
                DISPLAY TELACARGO
                MOVE "*** CARGO JA CADASTRAD0 ***" TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ACE-001
             ELSE
                MOVE "ERRO NA LEITURA ARQUIVO CADCARGO" TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM
           ELSE
                MOVE "*** CARGO NAO CADASTRAD0 ***" TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM.
       R3.
           ACCEPT TCARGO-DNMNC

           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02 GO TO R2.

           IF CARGO-DNMNC = SPACES
               MOVE "*** DENOMINACAO NAO INFORMADA  ***" TO MENS
               PERFORM ROT-MENS THRU ROT-MENS-FIM
               GO TO R3.


       R4.
           ACCEPT TCARGO-AREA

           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                   DISPLAY TELACARGO
                   GO TO R3

           IF CARGO-AREA = ZEROS
                 MOVE "*** AREA NAO INFORMADA  ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R4.

       R4A.
           MOVE CARGO-AREA TO AREA-COD.
           READ CADAREA
           IF ST-ERRO NOT = "00"
               IF ST-ERRO = "23"
                   MOVE "*** AREA DIGITADA NAO ENCONTRADO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R4
               ELSE
                  MOVE "ERRO NA LEITURA ARQUIVO CADASTRO AREA" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-FIM
           ELSE
                DISPLAY TELACARGO.
       R5.
           DISPLAY TELATS

           ACCEPT TCARGO-TPSAL
           ACCEPT W-ACT FROM ESCAPE KEY

           DISPLAY TELACARGO
           IF W-ACT = 02
                   GO TO R4.
           IF CARGO-TPSAL > 3 OR CARGO-TPSAL = 0
                MOVE "*** DIGITE APENAS DE 1 ATE 3***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R5.
       R5A.
           MOVE TBTPSAL(CARGO-TPSAL) TO TXTCARGO-TPSAL
           DISPLAY TTXTCARGO-TPSAL.
       R6.
           ACCEPT TCARGO-SALBASE

           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                   GO TO R5.

           IF CARGO-SALBASE = ZEROS
                 MOVE "*** SALARIO BASE NAO INFORMADO  ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R6.

      * ------------- VERICAR SE E ALTERACAO -----------------
           IF W-SEL = 1
                GO TO ALT-OPC.
       INC-OPC.
                MOVE "S" TO W-OPCAO
                DISPLAY (21, 17) "DADOS OK (S/N) : ".
                ACCEPT (21, 34) W-OPCAO WITH UPDATE
                ACCEPT W-ACT FROM ESCAPE KEY
                IF W-ACT = 01 GO TO R6.
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** DADOS RECUSADOS PELO OPERADOR ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "*** DIGITE APENAS S=SIM e N=NAO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-OPC.
       INC-WR1.
                WRITE REGCARGO
                IF ST-ERRO = "00" OR "02"
                      MOVE "*** DADOS GRAVADOS *** " TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO R1.
                IF ST-ERRO = "22"
                  MOVE "* CARGO JA EXISTE,DADOS NAO GRAVADOS *" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO R1
                ELSE
                      MOVE "ERRO NA GRAVACAO DO ARQUIVO DE PRODUTO"
                                                       TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO ROT-FIM.

      *
      *****************************************
      * ROTINA DE CONSULTA/ALTERACAO/EXCLUSAO *
      *****************************************
      *
       ACE-001.
                DISPLAY (21, 17)
                     "N=NOVO REGISTRO   A=ALTERAR   E=EXCLUIR"
                ACCEPT (21, 60) W-OPCAO
                IF W-OPCAO NOT = "N" AND W-OPCAO NOT = "A"
                    AND W-OPCAO NOT = "E" GO TO ACE-001.
                MOVE SPACES TO MENS
                DISPLAY (21, 17) MENS
                IF W-OPCAO = "N"
                   GO TO R1
                ELSE
                   IF W-OPCAO = "A"
                      MOVE 1 TO W-SEL
                      GO TO R3.
      *
       EXC-OPC.
                DISPLAY (21, 17) "EXCLUIR   (S/N) : ".
                ACCEPT (21, 34) W-OPCAO
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** REGISTRO NAO EXCLUIDO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "* DIGITE APENAS S=SIM  e  N=NAO *" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO EXC-OPC.
       EXC-DL1.
                DELETE CADCARGO RECORD
                IF ST-ERRO = "00"
                   MOVE "*** REGISTRO CARGO EXCLUIDO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                MOVE "ERRO NA EXCLUSAO DO REGISTRO "   TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM.
      *
       ALT-OPC.
                DISPLAY (21, 17) "ALTERAR  (S/N) : ".
                ACCEPT (21, 34) W-OPCAO
                ACCEPT W-ACT FROM ESCAPE KEY
                IF W-ACT = 01 GO TO R6.
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** INFORMACOES NAO ALTERADAS *** " TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "*** DIGITE APENAS S=SIM  e  N=NAO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO ALT-OPC.
       ALT-RW1.
                REWRITE REGCARGO
                IF ST-ERRO = "00" OR "02"
                   MOVE "*** REGISTRO ALTERADO ***         " TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                MOVE "ERRO NA EXCLUSAO DO REGISTRO CARGO"   TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM.
      *-------------------------------------------------------------------------------------------
       ROT-FIM.
           CLOSE CADCARGO CADAREA.
           STOP RUN.

      *---------[ ROTINA DE MENSAGEM ]---------------------
       ROT-MENS.
                MOVE ZEROS TO W-CONT.
       ROT-MENS1.
               DISPLAY (21, 17) MENS.
       ROT-MENS2.
                ADD 1 TO W-CONT
                IF W-CONT < 3000
                   GO TO ROT-MENS2
                ELSE
                   MOVE SPACES TO MENS
                   DISPLAY (21, 17) MENS.
       ROT-MENS-FIM.
                EXIT.
       FIM-ROT-TEMPO.

      *    FILE STATUS
      *    00 = OPERAÇÃO REALIZADO COM SUCESSO
      *    10 = FIM DE ARQUIVO ( QUANDO EXECUTADO LEITURA SEQUENCIAL )
      *    22 = REGISTRO JÁ CADASTRADO
      *    23 = REGISTRO NÃO ENCONTRADO
      *    30 = ARQUIVO NÃO ENCONTRADO
      *    95 = ISAM NÃO CARREGADO
