       IDENTIFICATION DIVISION.
       PROGRAM-ID. FPP002.
      * AUTHOR. GUSTAVO PEREIRA.
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
       77 MENS              PIC X(50) VALUE SPACES.
       77 LIMPA             PIC X(50) VALUE SPACES.
       77 TXTAREA-ESTRUTURA PIC X(20) VALUE SPACES.
      *
       01 TABESTRUTURA.
          03 TBESTRUTURA  PIC X(20) OCCURS 5 TIMES.
      *-----------------------------------------------------------------
       SCREEN SECTION.

       01  TELAAREA.
           05  BLANK SCREEN.
           05  LINE 01  COLUMN 01
               VALUE  "                            ÚÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 01  COLUMN 41
               VALUE  "ÄÄÄÄÄ¿".
           05  LINE 02  COLUMN 01
               VALUE  "                            ³CADASTRO DE".
           05  LINE 02  COLUMN 41
               VALUE  " AREA³".
           05  LINE 03  COLUMN 01
               VALUE  "                            ÀÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 03  COLUMN 41
               VALUE  "ÄÄÄÄÄÙ".
           05  LINE 04  COLUMN 01
               VALUE  " ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ".
           05  LINE 04  COLUMN 41
               VALUE  "ÍÍ» ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿".
           05  LINE 05  COLUMN 01
               VALUE  " º".
           05  LINE 05  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 06  COLUMN 01
               VALUE  " º  CODIGO AREA  :".
           05  LINE 06  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 07  COLUMN 01
               VALUE  " º".
           05  LINE 07  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 08  COLUMN 01
               VALUE  " º".
           05  LINE 08  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 09  COLUMN 01
               VALUE  " º".
           05  LINE 09  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 10  COLUMN 01
               VALUE  " º".
           05  LINE 10  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 11  COLUMN 01
               VALUE  " º  NOME DA AREA :".
           05  LINE 11  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 12  COLUMN 01
               VALUE  " º".
           05  LINE 12  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 13  COLUMN 01
               VALUE  " º".
           05  LINE 13  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 14  COLUMN 01
               VALUE  " º".
           05  LINE 14  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 15  COLUMN 01
               VALUE  " º".
           05  LINE 15  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 16  COLUMN 01
               VALUE  " º  ESTRUTURA    :".
           05  LINE 16  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 17  COLUMN 01
               VALUE  " º".
           05  LINE 17  COLUMN 41
               VALUE  "  º ³                                  ³".
           05  LINE 18  COLUMN 01
               VALUE  " ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ".
           05  LINE 18  COLUMN 41
               VALUE  "ÍÍ¼ ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ".
           05  LINE 19  COLUMN 01
               VALUE  " ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 19  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿".
           05  LINE 20  COLUMN 01
               VALUE  " ³".
           05  LINE 20  COLUMN 41
               VALUE  "                                       ³".
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
           05  TAREA-COD
               LINE 06  COLUMN 20  PIC 9(03)
               USING  AREA-COD
               HIGHLIGHT.
           05  TAREA-NOME
               LINE 11  COLUMN 20  PIC X(20)
               USING  AREA-NOME
               HIGHLIGHT.
           05  TAREA-ESTRUTURA
               LINE 16  COLUMN 20  PIC 9(01)
               USING  AREA-ESTRUTURA
               HIGHLIGHT.
           05  TTXTAREA-ESTRUTURA
               LINE 16  COLUMN 22  PIC X(20)
               USING  TXTAREA-ESTRUTURA
               HIGHLIGHT.


       01  TELAEST.
           05  LINE 09  COLUMN 51 VALUE  "1-PRESIDENCIA".
           05  LINE 10  COLUMN 51 VALUE  "2-DIRETORIA".
           05  LINE 11  COLUMN 51 VALUE  "3-GERENCIA".
           05  LINE 12  COLUMN 51 VALUE  "4-OPERACIONAL".
           05  LINE 13  COLUMN 51 VALUE  "5-CONSULTORIA".
      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
           MOVE "PRESIDENCIA" TO TBESTRUTURA(1)
           MOVE "DIRETORIA"   TO TBESTRUTURA(2)
           MOVE "GERENCIA"    TO TBESTRUTURA(3)
           MOVE "OPERACIONAL" TO TBESTRUTURA(4)
           MOVE "CONSULTORIA" TO TBESTRUTURA(5).

      *
       INC-OP0.
           OPEN I-O CADAREA
           IF ST-ERRO NOT = "00"
               IF ST-ERRO = "30"
                      OPEN OUTPUT CADAREA
                      CLOSE CADAREA
                      MOVE "* ARQUIVO AREA SENDO CRIADO *" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-OP0
               ELSE
                      MOVE "ERRO NA ABERTURA DO ARQUIVO AREA" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO ROT-FIM
           ELSE
                    NEXT SENTENCE.
       INC-001.
                MOVE SPACES TO AREA-NOME TXTAREA-ESTRUTURA
                MOVE ZEROS  TO AREA-COD AREA-ESTRUTURA.
                DISPLAY TELAAREA.
       INC-002.
                ACCEPT TAREA-COD
                ACCEPT W-ACT FROM ESCAPE KEY
                IF AREA-COD = ZEROS
                   MOVE "*** AREA INVALIDO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-002.
                IF W-ACT = 02
                   CLOSE CADAREA
                   GO TO ROT-FIM.
       LER-AREA01.
                MOVE 0 TO W-SEL
                READ CADAREA
                IF ST-ERRO NOT = "23"
                   IF ST-ERRO = "00"
                      PERFORM R1A
                      DISPLAY TELAAREA
                      MOVE "*** AREA JA CADASTRADO ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      MOVE 1 TO W-SEL
                      GO TO ACE-001
                   ELSE
                      MOVE "ERRO NA LEITURA ARQ. AREA"   TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO ROT-FIM
                ELSE
                   NEXT SENTENCE.
       R0.
                ACCEPT TAREA-NOME
                ACCEPT W-ACT FROM ESCAPE KEY
                IF W-ACT = 02 GO TO INC-002.
                IF AREA-NOME = SPACES
                   MOVE "NOME DEVE SER DIFERENTE DE BRANCOS" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R0.

       R1.
           DISPLAY TELAEST
           ACCEPT TAREA-ESTRUTURA.
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02 GO TO R0
           IF AREA-ESTRUTURA = 0 OR AREA-ESTRUTURA > 5
                 MOVE "*** DIGITE APENAS DE 1 ATE 5 ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R1.
       R1A.
           MOVE TBESTRUTURA(AREA-ESTRUTURA) TO TXTAREA-ESTRUTURA
           DISPLAY TTXTAREA-ESTRUTURA
           DISPLAY TELAAREA

           IF W-SEL = 02
                   GO TO ALT-OPC.
       INC-OPC.
                MOVE "S" TO W-OPCAO
                DISPLAY (21, 15) "DADOS OK (S/N) : ".
                ACCEPT (21, 32) W-OPCAO WITH UPDATE
                ACCEPT W-ACT FROM ESCAPE KEY
                IF W-ACT = 02 GO TO R1.
                IF W-OPCAO = "N" OR "n"
                   MOVE "* DADOS RECUSADOS PELO OPERADOR *" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-001.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "*** DIGITE APENAS S=SIM e N=NAO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-OPC.
       INC-WR1.
                WRITE REGAREA
                IF ST-ERRO = "00" OR "02"
                      MOVE "*** DADOS GRAVADOS *** " TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001.
                IF ST-ERRO = "22"
                      MOVE "*** NOTA JA EXISTE ***       " TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001
                ELSE
                      MOVE "ERRO NA GRAVACAO DO ARQUIVO DE NOTAS"
                                                       TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO ROT-FIM.
      *
      *****************************************
      * ROTINA DE CONSULTA/ALTERACAO/EXCLUSAO *
      *****************************************
      *
       ACE-001.
                DISPLAY (21, 15)
                     "F1=NOVO REGISTRO   F2=ALTERAR   F3=EXCLUIR"
                ACCEPT (21, 55) W-OPCAO
                ACCEPT W-ACT FROM ESCAPE KEY
                IF W-ACT NOT = 02 AND W-ACT NOT = 03 AND W-ACT NOT = 04
                   GO TO ACE-001.
                MOVE SPACES TO MENS
                DISPLAY (21, 15) MENS
                IF W-ACT = 02
                   GO TO INC-001.
                IF W-ACT = 03
                   MOVE 02 TO W-SEL
                   GO TO R0.


      *
       EXC-OPC.
                DISPLAY (21, 40) "EXCLUIR   (S/N) : ".
                ACCEPT (21, 57) W-OPCAO
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** REGISTRO NAO EXCLUIDO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-001.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "* DIGITE APENAS S=SIM  e  N=NAO *" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO EXC-OPC.
       EXC-DL1.
                DELETE CADAREA RECORD
                IF ST-ERRO = "00"
                   MOVE "*** REGISTRO EXCLUIDO ***        " TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-001.
                MOVE "ERRO NA EXCLUSAO DO REGISTRO "   TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM.
      *
       ALT-OPC.
                DISPLAY (21, 15) "ALTERAR  (S/N) : ".
                ACCEPT (21, 32) W-OPCAO
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** INFORMACOES NAO ALTERADAS *** " TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-001.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "* DIGITE APENAS S=SIM  e  N=NAO *" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO ALT-OPC.
       ALT-RW1.
                REWRITE REGAREA
                IF ST-ERRO = "00" OR "02"
                   MOVE "*** REGISTRO ALTERADO ***         " TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-001.
                MOVE "ERRO NA ALTERACAO DO REGISTRO AREA"   TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM.
      *
      **********************
      * ROTINA DE FIM      *
      **********************
      *
       ROT-FIM.
                DISPLAY (01, 01) ERASE
                EXIT PROGRAM.
       ROT-FIMP.
                EXIT PROGRAM.

       ROT-FIMS.
                STOP RUN.
      *
      **********************
      * ROTINA DE MENSAGEM *
      **********************
      *
       ROT-MENS.
                MOVE ZEROS TO W-CONT.
       ROT-MENS1.
               DISPLAY (21, 15) MENS.
       ROT-MENS2.
                ADD 1 TO W-CONT
                IF W-CONT < 3000
                   GO TO ROT-MENS2
                ELSE
                   DISPLAY (21, 15) LIMPA.
       ROT-MENS-FIM.
                EXIT.
       FIM-ROT-TEMPO.
