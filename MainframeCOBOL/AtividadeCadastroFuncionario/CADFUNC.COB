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
                    FILE STATUS  IS ST-ERRO.
      *
           SELECT CADCEP ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS CEP-NUMCEP
                    FILE STATUS  IS ST-ERRO
                    ALTERNATE RECORD KEY IS CEP-LOGRA
                                   WITH DUPLICATES.
      *
      *-----------------------------------------------------------------
       DATA DIVISION.
       FILE SECTION.
       FD CADFUNC
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADFUNC.DAT".
       01 REGFUNC.
          03 CODFUNC    PIC 9(5).
          03 NOME       PIC X(35).
          03 TPSALARIO  PIC 9(1).
          03 CODCARGO   PIC 9(3).
          03 CODDEP     PIC 9(3).
          03 SALBASE    PIC 9(6)V99.
          03 ENDERECO   PIC 9(8).
          03 NENDRC     PIC 9(5).
          03 COMPENDRC  PIC X(10).
          03 EMAIL      PIC X(30).
          03 TELEFONE.
             05 DDD     PIC 9(2).
             05 NUMERO  PIC 9(9).
          03 SITFUNC    PIC X(1).
      *
      *-----------------------------------------------------------------
       FD CADCEP
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADCEP.DAT".
       01 REGCEP.
                03 CEP-NUMCEP        PIC 9(08).
                03 CEP-LOGRA         PIC X(30).
                03 CEP-BAIRRO        PIC X(20).
                03 CEP-CIDADE        PIC X(20).
                03 CEP-UF            PIC X(02).
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       77 W-CONT        PIC 9(06) VALUE ZEROS.
       77 W-OPCAO       PIC X(01) VALUE SPACES.
       77 W-ACT         PIC 9(02) VALUE ZEROS.
       77 MENS          PIC X(50) VALUE SPACES.
       77 LIMPA         PIC X(50) VALUE SPACES.
       01 ST-ERRO       PIC X(02) VALUE "00".
       01 W-SEL         PIC 9(01) VALUE ZEROS.
       01 IND           PIC 9(02) VALUE ZEROS.
       01 TXTTPSALARIO  PIC X(15) VALUE SPACES.
       01 TXTCARGO      PIC X(15) VALUE SPACES.
       01 TXTDEP        PIC X(12) VALUE SPACES.
       01 TXTENDERECO   PIC X(40) VALUE SPACES.

       01 TABTPSAL.
          03 TBTPSAL   PIC X(10) OCCURS 3 TIMES.

       01 TABDEP.
          03 TBDEP     PIC X(20) OCCURS 10 TIMES.

       01 TABSITFUNCX.
          03 FILLER     PIC X(15) VALUE "AATIVO".
          03 FILLER     PIC X(15) VALUE "DDESLIGADO".
          03 FILLER     PIC X(15) VALUE "FAFASTADO".
          03 FILLER     PIC X(15) VALUE "NNAO DECLARADO".
       01 TABSITFUNC REDEFINES TABSITFUNCX.
          03 TBSITFUNC   PIC X(15) OCCURS 4 TIMES.
       01 TXTSITFUNC.
          03 TXTSITFUNC1 PIC X(01) VALUE SPACES.
          03 TXTSITFUNC2 PIC X(14) VALUE SPACES.

      *-----------------------------------------------------------------
       SCREEN SECTION.
       01  TELAFUNC.
           05  BLANK SCREEN.
           05  LINE 01  COLUMN 01
               VALUE  "                                        ".
           05  LINE 01  COLUMN 41
               VALUE  "     Ŀ".
           05  LINE 02  COLUMN 01
               VALUE  "                          CADASTRO FUNCI".
           05  LINE 02  COLUMN 41
               VALUE  "ONARIO ".
           05  LINE 03  COLUMN 01
               VALUE  "                                        ".
           05  LINE 03  COLUMN 41
               VALUE  "       ".
           05  LINE 04  COLUMN 01
               VALUE  "                                        ".
           05  LINE 04  COLUMN 41
               VALUE  "                                      ͻ".
           05  LINE 05  COLUMN 01
               VALUE  " CODIGO FUNCIONARIO   :".
           05  LINE 05  COLUMN 41
               VALUE  "                                        ".
           05  LINE 06  COLUMN 01
               VALUE  " NOME                 :".
           05  LINE 06  COLUMN 41
               VALUE  "                                        ".
           05  LINE 07  COLUMN 01
               VALUE  " ".
           05  LINE 07  COLUMN 41
               VALUE  "                                        ".
           05  LINE 08  COLUMN 01
               VALUE  " TIPO DE SALARIO      :".
           05  LINE 08  COLUMN 41
               VALUE  "                                        ".
           05  LINE 09  COLUMN 01
               VALUE  " COD CARGO            :".
           05  LINE 09  COLUMN 41
               VALUE  "    COD DEPARTAMENTO :                  ".
           05  LINE 10  COLUMN 01
               VALUE  " SALARIO BASE         :                 ".
           05  LINE 10  COLUMN 41
               VALUE  "                                     Ŀ ".
           05  LINE 11  COLUMN 01
               VALUE  "                                   ".
           05  LINE 11  COLUMN 41
               VALUE  "           DADOS ENDERECO               ".
           05  LINE 12  COLUMN 01
               VALUE  " ENDERECO             :            LOGRD".
           05  LINE 12  COLUMN 41
               VALUE  " :                                      ".
           05  LINE 13  COLUMN 01
               VALUE  " NUMERO ENDERECO      :            BAIRR".
           05  LINE 13  COLUMN 41
               VALUE  "O:                                      ".
           05  LINE 14  COLUMN 01
               VALUE  " COMPLEMENTO ENDERECO :            CIDAD".
           05  LINE 14  COLUMN 41
               VALUE  "E:                       UF:            ".
           05  LINE 15  COLUMN 01
               VALUE  "                                        ".
           05  LINE 15  COLUMN 41
               VALUE  "                                      ٺ".
           05  LINE 16  COLUMN 01
               VALUE  " EMAIL                :".
           05  LINE 16  COLUMN 41
               VALUE  "                                        ".
           05  LINE 17  COLUMN 01
               VALUE  " TELEFONE             :".
           05  LINE 17  COLUMN 41
               VALUE  "                                        ".
           05  LINE 18  COLUMN 01
               VALUE  " SITUACAO FUNCIONAL   :".
           05  LINE 18  COLUMN 41
               VALUE  "                                        ".
           05  LINE 19  COLUMN 01
               VALUE  "                                        ".
           05  LINE 19  COLUMN 41
               VALUE  "                                      ͼ".
           05  LINE 20  COLUMN 01
               VALUE  "                                        ".
           05  LINE 20  COLUMN 41
               VALUE  "Ŀ                                    Ŀ".
           05  LINE 21  COLUMN 01
               VALUE  " MENSAGEM".
           05  LINE 21  COLUMN 41
               VALUE  "                                        ".
           05  LINE 22  COLUMN 01
               VALUE  " ".
           05  LINE 22  COLUMN 41
               VALUE  "                                        ".
           05  LINE 23  COLUMN 01
               VALUE  " ".
           05  LINE 23  COLUMN 41
               VALUE  "                                        ".
           05  LINE 24  COLUMN 01
               VALUE  "                                        ".
           05  LINE 24  COLUMN 41
               VALUE  "                                        ".
           05  TCODFUNC
               LINE 05  COLUMN 25  PIC 9(05)
               USING  CODFUNC
               HIGHLIGHT.
           05  TNOME
               LINE 06  COLUMN 25  PIC X(35)
               USING  NOME
               HIGHLIGHT.
           05  TTPSALARIO
               LINE 08  COLUMN 25  PIC 9(01)
               USING  TPSALARIO
               HIGHLIGHT.
           05  TTXTTPSALARIO
               LINE 08  COLUMN 29  PIC X(15)
               USING  TXTTPSALARIO
               HIGHLIGHT.
           05  TCODCARGO
               LINE 09  COLUMN 25  PIC 9(03)
               USING  CODCARGO
               HIGHLIGHT.
           05  TTXTCARGO
               LINE 09  COLUMN 29  PIC X(15)
               USING  TXTCARGO
               HIGHLIGHT.
           05  TCODDEP
               LINE 09  COLUMN 64  PIC 9(03)
               USING  CODDEP
               HIGHLIGHT.
           05  TTXTDEP
               LINE 09  COLUMN 68  PIC X(12)
               USING  TXTDEP
               HIGHLIGHT.
           05  TSALBASE
               LINE 10  COLUMN 25  PIC 9(6),99
               USING  SALBASE
               HIGHLIGHT.
           05  TENDERECO
               LINE 12  COLUMN 25  PIC 9(08)
               USING  ENDERECO
               HIGHLIGHT.
           05  TCEP-LOGRA
               LINE 12  COLUMN 44  PIC X(35)
               USING  CEP-LOGRA
               HIGHLIGHT.
           05  TCEP-BAIRRO
               LINE 13  COLUMN 44  PIC X(20)
               USING  CEP-BAIRRO
               HIGHLIGHT.
           05  TCEP-CIDADE
               LINE 14  COLUMN 44  PIC X(20)
               USING  CEP-CIDADE
               HIGHLIGHT.
           05  TCEP-UF
               LINE 14  COLUMN 70  PIC X(02)
               USING  CEP-UF
               HIGHLIGHT.
           05  TNENDRC
               LINE 13  COLUMN 25  PIC 9(05)
               USING  NENDRC
               HIGHLIGHT.
           05  TCOMPENDRC
               LINE 14  COLUMN 25  PIC X(10)
               USING  COMPENDRC
               HIGHLIGHT.
           05  TEMAIL
               LINE 16  COLUMN 25  PIC X(30)
               USING  EMAIL
               HIGHLIGHT.
           05  TDDD
               LINE 17  COLUMN 25  PIC 9(02)
               USING  DDD
               HIGHLIGHT.
           05  TNUMERO
               LINE 17  COLUMN 28  PIC 9(09)
               USING  NUMERO
               HIGHLIGHT.
           05  TSITFUNC
               LINE 18  COLUMN 25  PIC X(01)
               USING  SITFUNC
               HIGHLIGHT.
           05  TTXTSITFUNC
               LINE 18  COLUMN 28  PIC X(10)
               USING  TXTSITFUNC
               HIGHLIGHT.
      *
       01  TELACD.
           05  LINE 21  COLUMN 14 VALUE  "1-INFORMATICA".
           05  LINE 21  COLUMN 33 VALUE  "2-CONTABILIDADE".
           05  LINE 21  COLUMN 49 VALUE  "3-VENDAS".
           05  LINE 22  COLUMN 14 VALUE  "4-RECURSOS HUMANOS".
           05  LINE 22  COLUMN 33 VALUE  "5-FINANCEIRO".
           05  LINE 22  COLUMN 49 VALUE  "6-OPERACIONAL".
           05  LINE 23  COLUMN 14 VALUE  "7-GESTAO".
           05  LINE 23  COLUMN 33 VALUE  "8-ADMINISTRACAO".
           05  LINE 23  COLUMN 49 VALUE  "9-MARKETING".
           05  LINE 23  COLUMN 63 VALUE  "10-LOGISTICA".
       01  TELATS.
           05  LINE 21  COLUMN 38 VALUE  "1-HORISTA".
           05  LINE 22  COLUMN 38 VALUE  "2-DIARISTA".
           05  LINE 23  COLUMN 38 VALUE  "3-MENSALISTA".
       01 TELATPSF.
          05 LINE 21  COLUMN 38 "A-ATIVO".
          05 LINE 22  COLUMN 38 "D-DESLIGADO".
          05 LINE 23  COLUMN 38 "F-AFASTADO".

      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
           MOVE "INFORMATICA"      TO TBDEP(1)
           MOVE "CONTABILIDADE"    TO TBDEP(2)
           MOVE "VENDAS"           TO TBDEP(3)
           MOVE "RECURSOS HUMANOS" TO TBDEP(4)
           MOVE "FINANCEIRO"       TO TBDEP(5)
           MOVE "OPERACIONAL"      TO TBDEP(6)
           MOVE "GESTAO"           TO TBDEP(7)
           MOVE "ADMINISTRACAO"    TO TBDEP(8)
           MOVE "MARKETING"        TO TBDEP(9)
           MOVE "LOGISTICA"        TO TBDEP(10)

           MOVE "HORISTA"          TO TBTPSAL(1)
           MOVE "DIARISTA"         TO TBTPSAL(2)
           MOVE "MENSALISTA"       TO TBTPSAL(3).

       R0.
           OPEN I-O CADFUNC
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 OPEN OUTPUT CADFUNC
                 CLOSE CADFUNC
                 MOVE "*** ARQUIVO CAFUNC FOI CRIADO **" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R0
              ELSE
                 MOVE "ERRO NA ABERTURA DO ARQUIVO CADFUNC" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
                 NEXT SENTENCE.
       R0A.
           OPEN I-O CADCEP
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "*** ARQUIVO DE CEP NAO ENCONTRADO **" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
              ELSE
                 MOVE "ERRO NA ABERTURA DO ARQUIVO DE CEP " TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
                 NEXT SENTENCE.

      *
       R1.
           MOVE SPACES TO NOME COMPENDRC EMAIL COMPENDRC
           MOVE SPACES TO CEP-LOGRA CEP-BAIRRO CEP-CIDADE CEP-UF
           MOVE SPACES TO TXTTPSALARIO TXTCARGO TXTDEP
           MOVE ZEROS TO CODFUNC TPSALARIO CODCARGO CODDEP SALBASE
           MOVE ZEROS TO ENDERECO NENDRC DDD NUMERO W-SEL
           MOVE ZEROS TO CEP-NUMCEP
           DISPLAY TELAFUNC.
       R2.
           ACCEPT TCODFUNC
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO ROT-FIM.

       LER-CADFUNC.
           READ CADFUNC
           IF ST-ERRO NOT = "23"
             IF ST-ERRO = "00"
                PERFORM R4A
                PERFORM R6A
                PERFORM R8A
                PERFORM R13A
                DISPLAY TELAFUNC
                MOVE "*** FUNCIONARIO JA CADASTRAD0 ***" TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ACE-001
             ELSE
                MOVE "ERRO NA LEITURA ARQUIVO CADFUNC" TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM
           ELSE
                NEXT SENTENCE.

       R3.
           ACCEPT TNOME
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R2.

       R4.
           DISPLAY TELATS
           
           ACCEPT TTPSALARIO
           ACCEPT W-ACT FROM ESCAPE KEY
           
           DISPLAY TELAFUNC
           IF W-ACT = 01
                   GO TO R3.
           IF TPSALARIO > 3 OR TPSALARIO = 0
           	 MOVE "*** DIGITE APENAS DE 1 ATE 3***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R4.
       R4A.
           MOVE TBTPSAL(TPSALARIO) TO TXTTPSALARIO
           DISPLAY TTXTTPSALARIO.
       R5.
           ACCEPT TCODCARGO
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R4.
       R6.
	   DISPLAY TELACD

           ACCEPT TCODDEP
           ACCEPT W-ACT FROM ESCAPE KEY

	   DISPLAY TELAFUNC
           IF W-ACT = 01
                   GO TO R5.
           IF CODDEP > 10 OR CODDEP = 0
           	 MOVE "*** DIGITE APENAS DE 1 ATE 10***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R6.
       R6A.
           MOVE TBDEP(CODDEP) TO TXTDEP
           DISPLAY TTXTDEP
           DISPLAY TELAFUNC.
       R7.
           ACCEPT TSALBASE
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R6.
       R8.
           ACCEPT TENDERECO.
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R7.
       R8A.
           MOVE ENDERECO TO CEP-NUMCEP.
           READ CADCEP
           IF ST-ERRO NOT = "00"
               IF ST-ERRO = "23"
                   MOVE "*** CEP DIGITADO NAO ENCONTRADO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R8
               ELSE
                  MOVE "ERRO NA LEITURA ARQUIVO CADASTRO CEP" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-FIM
           ELSE
                DISPLAY TELAFUNC.
       R9.
           ACCEPT TNENDRC.
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R8.
       R10.
           ACCEPT TCOMPENDRC
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R9.
       R11.
           ACCEPT TEMAIL
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R10.
       R12.
           ACCEPT TDDD
           ACCEPT TNUMERO
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R11.
       R13.
           DISPLAY TELATPSF
           MOVE 0 TO IND
           ACCEPT TSITFUNC.
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R12.
       R13A.
           MOVE TBSITFUNC(IND) TO TXTSITFUNC
           IF TXTSITFUNC1 NOT = SITFUNC
              ADD 1 TO IND
              IF IND < 4
                 GO TO R13A
              ELSE
                 MOVE "*** TIPO SITUACAO FUNC INCORRETO***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R13
           ELSE
               DISPLAY TXTSITFUNC2.

       INC-OPC.
                MOVE "S" TO W-OPCAO
                DISPLAY (23, 40) "DADOS OK (S/N) : "
                ACCEPT (23, 57) W-OPCAO WITH UPDATE
                ACCEPT W-ACT FROM ESCAPE KEY
                IF W-ACT = 01 GO TO R7.
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** DADOS RECUSADOS PELO OPERADOR ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "*** DIGITE APENAS S=SIM e N=NAO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-OPC.
       INC-WR1.
                WRITE REGFUNC
                IF ST-ERRO = "00" OR "02"
                      MOVE "*** DADOS GRAVADOS *** " TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO R1
                IF ST-ERRO = "22"
                  MOVE "* FUNC JA EXISTE,DADOS NAO GRAVADOS *" TO MENS
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
                DELETE CADFUNC RECORD
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
                IF W-ACT = 01 GO TO R7.
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** INFORMACOES NAO ALTERADAS *** " TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "*** DIGITE APENAS S=SIM  e  N=NAO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO ALT-OPC.
       ALT-RW1.
                REWRITE REGFUNC
                IF ST-ERRO = "00" OR "02"
                   MOVE "*** REGISTRO ALTERADO ***         " TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                MOVE "ERRO NA EXCLUSAO DO REGISTRO AMIGO"   TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM.
      *-------------------------------------------------------------------------------------------
       ROT-FIM.
           CLOSE CADFUNC CADCEP.
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
