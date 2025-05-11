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
           SELECT CADAREA ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS AREA-COD
                    FILE STATUS  IS ST-ERRO
                    ALTERNATE RECORD KEY IS AREA-NOME
                                   WITH DUPLICATES.
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
       FD CADCEP
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADCEP.DAT".
       01 REGCEP.
                03 CEP-NUMCEP        PIC 9(08).
                03 CEP-LOGRA         PIC X(30).
                03 CEP-BAIRRO        PIC X(20).
                03 CEP-CIDADE        PIC X(20).
                03 CEP-UF            PIC X(02).
      *
      *-----------------------------------------------------------------
       FD CADAREA
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADAREA.DAT".
       01 REGAREA.
                03 AREA-COD          PIC 9(03).
                03 AREA-NOME         PIC X(20).
                03 AREA-ESTRUTURA    PIC 9(1).
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
       01 TXTDEP        PIC X(15) VALUE SPACES.
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
               VALUE  "                       ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 01  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄ¿".
           05  LINE 02  COLUMN 01
               VALUE  "                       ³ CADASTRO DE FUN".
           05  LINE 02  COLUMN 41
               VALUE  "CIONARIO ³".
           05  LINE 03  COLUMN 01
               VALUE  "                       ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 03  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÙ".
           05  LINE 04  COLUMN 01
               VALUE  "ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ".
           05  LINE 04  COLUMN 41
               VALUE  "ÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ»".
           05  LINE 05  COLUMN 01
               VALUE  "ºÚÄÄÄÄÄDADOS PESSOAISÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 05  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿º".
           05  LINE 06  COLUMN 01
               VALUE  "º³ CODIGO FUNCIONARIO :       NUM DEPEND".
           05  LINE 06  COLUMN 41
               VALUE  "ENTES  :                              ³º".
           05  LINE 07  COLUMN 01
               VALUE  "º³ NOME               :".
           05  LINE 07  COLUMN 41
               VALUE  "                                      ³º".
           05  LINE 08  COLUMN 01
               VALUE  "º³ TELEFONE           : (  )".
           05  LINE 08  COLUMN 41
               VALUE  " EMAIL :                              ³º".
           05  LINE 09  COLUMN 01
               VALUE  "ºÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 09  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙº".
           05  LINE 10  COLUMN 01
               VALUE  "ºÚÄÄÄÄÄDADOS ENDERECO ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 10  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿º".
           05  LINE 11  COLUMN 01
               VALUE  "º³ CEP (ENDERECO)      :            LOGR".
           05  LINE 11  COLUMN 41
               VALUE  "ADOURO :                              ³º".
           05  LINE 12  COLUMN 01
               VALUE  "º³ NUMERO ENDERECO     :            BAIR".
           05  LINE 12  COLUMN 41
               VALUE  "RO     :                              ³º".
           05  LINE 13  COLUMN 01
               VALUE  "º³ COMPLEMENTO ENDERCO :            CIDA".
           05  LINE 13  COLUMN 41
               VALUE  "DE     :                     UF :     ³º".
           05  LINE 14  COLUMN 01
               VALUE  "ºÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 14  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙº".
           05  LINE 15  COLUMN 01
               VALUE  "ºÚÄÄÄÄÄDADOS PROFISSIONAISÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 15  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿º".
           05  LINE 16  COLUMN 01
               VALUE  "º³ CODIGO AREA    :".
           05  LINE 16  COLUMN 41
               VALUE  " CODIGO CARGO :       SITUACAO :      ³º".
           05  LINE 17  COLUMN 01
               VALUE  "º³ NOME AREA      :".
           05  LINE 17  COLUMN 41
               VALUE  " DENOMINACAO  :                       ³º".
           05  LINE 18  COLUMN 01
               VALUE  "º³ ESTRUTURA AREA :".
           05  LINE 18  COLUMN 41
               VALUE  " TIPO SALARIO :   S. BASE :           ³º".
           05  LINE 19  COLUMN 01
               VALUE  "º³ DT ADMISSAO :          DT DEMISSAO :".
           05  LINE 19  COLUMN 41
               VALUE  "         DT AFASTAMENTO :             ³º".
           05  LINE 20  COLUMN 01
               VALUE  "ºÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 20  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙº".
           05  LINE 21  COLUMN 01
               VALUE  "ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ".
           05  LINE 21  COLUMN 41
               VALUE  "ÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ¼".
           05  LINE 22  COLUMN 01
               VALUE  "ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 22  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿".
           05  LINE 23  COLUMN 01
               VALUE  "³MENSAGEM :".
           05  LINE 23  COLUMN 41
               VALUE  "                                       ³".
           05  LINE 24  COLUMN 01
               VALUE  "ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 24  COLUMN 41
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ".
           05  TCODFUNC
               LINE 06  COLUMN 25  PIC 9(05)
               USING  CODFUNC
               HIGHLIGHT.
           05  TNDEPS
               LINE 06  COLUMN 49  PIC 9(02)
               USING  NDEPS
               HIGHLIGHT.
           05  TNOME
               LINE 07  COLUMN 25  PIC X(35)
               USING  NOME
               HIGHLIGHT.
           05  TDDD
               LINE 08  COLUMN 26  PIC 9(02)
               USING  DDD
               HIGHLIGHT.
           05  TNUMERO
               LINE 08  COLUMN 29  PIC 9(09)
               USING  NUMERO
               HIGHLIGHT.
           05  TEMAIL
               LINE 08  COLUMN 49  PIC X(30)
               USING  EMAIL
               HIGHLIGHT.
           05  TENDERECO
               LINE 11  COLUMN 26  PIC 9(08)
               USING  ENDERECO
               HIGHLIGHT.
           05  TCEP-LOGRA
               LINE 11  COLUMN 49  PIC X(30)
               USING  CEP-LOGRA
               HIGHLIGHT.
           05  TNENDRC
               LINE 12  COLUMN 26  PIC 9(05)
               USING  NENDRC
               HIGHLIGHT.
           05  TCEP-BAIRRO
               LINE 12  COLUMN 49  PIC X(20)
               USING  CEP-BAIRRO
               HIGHLIGHT.
           05  TCOMPENDRC
               LINE 13  COLUMN 26  PIC X(10)
               USING  COMPENDRC
               HIGHLIGHT.
           05  TCEP-CIDADE
               LINE 13  COLUMN 49  PIC X(20)
               USING  CEP-CIDADE
               HIGHLIGHT.
           05  TCEP-UF
               LINE 13  COLUMN 75  PIC X(02)
               USING  CEP-UF
               HIGHLIGHT.
           05  TCODAREA
               LINE 16  COLUMN 21  PIC 9(03)
               USING  CODAREA
               HIGHLIGHT.
           05  TCODCARGO
               LINE 16  COLUMN 57  PIC 9(05)
               USING  CODCARGO
               HIGHLIGHT.
           05  TSITUNC
               LINE 16  COLUMN 74  PIC X(01)
               USING  SITFUNC
               HIGHLIGHT.
           05  TAREA-NOME
               LINE 17  COLUMN 21  PIC X(20)
               USING  AREA-NOME
               HIGHLIGHT.
           05  TCARGO-DNMNC
               LINE 17  COLUMN 57  PIC X(20)
               USING  CARGO-DNMNC
               HIGHLIGHT.
           05  TAREA-ESTRUTURA
               LINE 18  COLUMN 21  PIC 9(01)
               USING  AREA-ESTRUTURA
               HIGHLIGHT.
           05  TCARGO-TPSAL
               LINE 18  COLUMN 57  PIC 9(01)
               USING  CARGO-TPSAL
               HIGHLIGHT.
           05  TCARGO-SALBASE
               LINE 18  COLUMN 69  PIC 9(06),99
               USING  CARGO-SALBASE
               HIGHLIGHT.
           05  TDTADMISSAO
               LINE 19  COLUMN 18  PIC 9(08)
               USING  DTADMISSAO
               HIGHLIGHT.
           05  TDTDEMISSAO
               LINE 19  COLUMN 41  PIC 9(08)
               USING  DTDEMISSAO
               HIGHLIGHT.
           05  TDTAFAST
               LINE 19  COLUMN 67  PIC 9(08)
               USING  DTAFAST
               HIGHLIGHT.

      *

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
       R0B.
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
       R0C.
           OPEN I-O CADCARGO
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "*** ARQUIVO DE CARGO NAO ENCONTRADO **" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
              ELSE
                 MOVE "ERRO NA ABERTURA DO ARQUIVO DE CARGO " TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
                 NEXT SENTENCE.
      *
       R1.
           MOVE SPACES TO NOME COMPENDRC EMAIL COMPENDRC
           MOVE SPACES TO CEP-LOGRA CEP-BAIRRO CEP-CIDADE CEP-UF
           MOVE SPACES TO TXTTPSALARIO TXTCARGO TXTDEP
           MOVE ZEROS TO CODFUNC CODCARGO CODDEP NDEPS CODAREA
           MOVE ZEROS TO ENDERECO NENDRC DDD NUMERO W-SEL CARGO-SALBASE
           MOVE ZEROS TO CEP-NUMCEP AREA-ESTRUTURA CARGO-TPSAL
           MOVE ZEROS TO DTADMISSAO DTDEMISSAO DTAFAST
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
                PERFORM R7A
                PERFORM R10A
                PERFORM R11A
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
           ACCEPT TNDEPS
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R2.

       R4.
           ACCEPT TNOME
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R2.

       R5.
           ACCEPT TDDD
           ACCEPT TNUMERO
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R4.
       R6.
           ACCEPT TEMAIL
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R5.
       R7.
           ACCEPT TENDERECO.
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R7.
       R7A.
           MOVE ENDERECO TO CEP-NUMCEP.
           READ CADCEP
           IF ST-ERRO NOT = "00"
               IF ST-ERRO = "23"
                   MOVE "*** CEP DIGITADO NAO ENCONTRADO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R7
               ELSE
                  MOVE "ERRO NA LEITURA ARQUIVO CADASTRO CEP" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-FIM
           ELSE
                DISPLAY TELAFUNC.

       R8.
           ACCEPT TNENDRC.
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R8.

       R9.
           ACCEPT TCOMPENDRC
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R9.

       R10.
           ACCEPT TCODAREA.
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R7.
       R10A.
           MOVE CODAREA TO AREA-COD.
           READ CADAREA
           IF ST-ERRO NOT = "00"
               IF ST-ERRO = "23"
                   MOVE "*** AREA DIGITADA NAO ENCONTRADA ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R7
               ELSE
                  MOVE "ERRO NA LEITURA ARQUIVO CADASTRO AREA" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-FIM
           ELSE
                DISPLAY TELAFUNC.

       R11.
           ACCEPT TCODCARGO.
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R7.
       R11A.
           MOVE CODCARGO TO CARGO-COD.
           READ CADCARGO
           IF ST-ERRO NOT = "00"
               IF ST-ERRO = "23"
                   MOVE "*** CARGO DIGITADO NAO ENCONTRADO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R7
               ELSE
                  MOVE "ERRO NA LEITURA ARQUIVO CADASTRO CARGO" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO ROT-FIM
           ELSE
                DISPLAY TELAFUNC.

       R12A.
           ACCEPT TDTADMISSAO
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R11.
       R12B.
           ACCEPT TDTADMISSAO
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R12A.
       R12C.
           ACCEPT TDTADMISSAO
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R12C.
       R12.
           IF DTADMISSAO IS NOT = ZEROS
               IF DTDEMISSAO IS NOT = ZEROS OR DTAFAST IS NOT = ZEROS
                   MOVE "*** SO UMA DATA DEVE TER DADOS!  ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
               ELSE
                   MOVE "A" TO SITFUNC
           ELSE IF DTDEMISSAO IS NOT = ZEROS
               IF DTDEMISSAO IS NOT = ZEROS OR DTAFAST IS NOT = ZEROS
                   MOVE "*** SO UMA DATA DEVE TER DADOS!  ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
               ELSE
                   MOVE "D" TO SITFUNC
           ELSE
               IF DTADMISSAO IS NOT = ZEROS OR DTDEMISSAO IS NOT = ZEROS
                   MOVE "*** SO UMA DATA DEVE TER DADOS!  ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
               ELSE
                   MOVE "F" TO SITFUNC.


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
                MOVE "ERRO NA EXCLUSAO DO REGISTRO FUNC"   TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM.
      *-------------------------------------------------------------------------------------------
       ROT-FIM.
           CLOSE CADFUNC CADCEP CADAREA CADCARGO.
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
