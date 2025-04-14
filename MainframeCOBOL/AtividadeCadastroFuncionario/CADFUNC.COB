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
      *
      *-----------------------------------------------------------------
       DATA DIVISION.
       FILE SECTION.
       FD CADFUNC
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADFUNC.DAT". 
       01 REGCADFUNC.
          03 CODFUNC    PIC X(5).
          03 NOME       PIC X(35).
          03 TPSALARIO  PIC 9(1).
          03 CODCARGO   PIC 9(3).
          03 CODDEP     PIC X(3).
          03 SALBASE    PIC 9(6)V99.
          03 ENDERECO   PIC 9(8).
          03 NUMENDRC   PIC 9(5).
          03 COMPENDRC  PIC X(10).
          03 EMAIL      PIC X(30).
          03 TELEFONE.
             05 DDD     PIC 9(2).
             05 NUMERO  PIC 9(9).
          03 SITFUNC    PIC 9(1).
      *
      *-----------------------------------------------------------------
       WORKING-STORAGE SECTION.
       77 W-CONT        PIC 9(06) VALUE ZEROS.
       77 W-OPCAO       PIC X(01) VALUE SPACES.
       77 W-ACT         PIC 9(02) VALUE ZEROS.
       77 MENS          PIC X(50) VALUE SPACES.
       77 LIMPA         PIC X(50) VALUE SPACES. 
       01 ST-ERRO       PIC X(02) VALUE "00".
       01 W-SEL         PIC 9(01) VALUE ZEROS.
       01 TXTTPAMIGO    PIC X(10) VALUE SPACES.
       01 IND           PIC 9(02) VALUE ZEROS.
       01 TEXSEXO       PIC X(12) VALUE SPACES.

       01 TABTPSAL.
          03 TBATPSAL   PIC X(10) OCCURS 3 TIMES.

       01 TABDEP.
          03 TBADEP     PIC X(20) OCCURS 10 TIMES.

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
               VALUE  " SALARIO BASE         :".
           05  LINE 10  COLUMN 41 
               VALUE  "                                        ".
           05  LINE 11  COLUMN 01 
               VALUE  " ".
           05  LINE 11  COLUMN 41 
               VALUE  "                                        ".
           05  LINE 12  COLUMN 01 
               VALUE  " ENDERECO             :".
           05  LINE 12  COLUMN 41 
               VALUE  "                                        ".
           05  LINE 13  COLUMN 01 
               VALUE  " NUMERO ENDERECO      :".
           05  LINE 13  COLUMN 41 
               VALUE  "                                        ".
           05  LINE 14  COLUMN 01 
               VALUE  " COMPLEMENTO ENDERECO :".
           05  LINE 14  COLUMN 41 
               VALUE  "                                        ".
           05  LINE 15  COLUMN 01 
               VALUE  " ".
           05  LINE 15  COLUMN 41 
               VALUE  "                                        ".
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
           05  TTXTENDERECO
               LINE 12  COLUMN 34  PIC X(40)
               USING  TXTENDERECO
               HIGHLIGHT.
           05  TNUMENDRC
               LINE 13  COLUMN 25  PIC 9(05)
               USING  NUMENDRC
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
               LINE 18  COLUMN 25  PIC 9(01)
               USING  SITFUNC
               HIGHLIGHT.
           05  TTXTSITFUNC
               LINE 18  COLUMN 28  PIC X(10)
               USING  TXTSITFUNC
               HIGHLIGHT.
      *
       01  TELACD.
           05  LINE 14  COLUMN 41 VALUE  "1-INFORMATICA".
           05  LINE 15  COLUMN 41 VALUE  "2-CONTABILIDADE".
           05  LINE 16  COLUMN 41 VALUE  "3-VENDAS".
           05  LINE 17  COLUMN 41 VALUE  "4-RECURSOS HUMANOS".
           05  LINE 18  COLUMN 41 VALUE  "5-FINANCEIRO".
           05  LINE 19  COLUMN 41 VALUE  "6-OPERACIONAL".
           05  LINE 20  COLUMN 41 VALUE  "7-GESTAO".
           05  LINE 21  COLUMN 41 VALUE  "8-ADMINISTRACAO".
           05  LINE 22  COLUMN 41 VALUE  "9-MARKETING".
           05  LINE 22  COLUMN 41 VALUE  "10-LOGISTICA".
       01  TELATS.
           05  LINE 14  COLUMN 41 VALUE  "1-HORISTA".
           05  LINE 15  COLUMN 41 VALUE  "2-DIARISTA".
           05  LINE 16  COLUMN 41 VALUE  "3-MENSALISTA".

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
      *                
       R1.
           MOVE SPACES TO NOME COMPENDRC SITFUNC
           MOVE SPACES TO TXTSITFUNC TXTTPSAL TXTDEP
           MOVE ZEROS TO CODFUNC TPSAL CODCARGO CODDEP SALBASE ENDERECO NUMENDERC COMPENDRC DDD NUMERO
           DISPLAY TELAFUNC.
       R2.
           ACCEPT TAPELIDO
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO ROT-FIM.
       LER-CADMAMIGO.
           READ CADAMIGO
           IF ST-ERRO NOT = "23"
             IF ST-ERRO = "00"
                PERFORM R5A
                PERFORM R6A
                PERFORM R7A
                DISPLAY TELA2
                MOVE "*** AMIGO JA CADASTRAD0 ***" TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO R1
             ELSE
                MOVE "ERRO NA LEITURA ARQUIVO CADAMIGO" TO MENS
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
           ACCEPT TEMAIL.  
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R3.
       R5.
           ACCEPT TSEXO.
       R5A.
           IF SEXO = "M" OR SEXO = "m"
              MOVE "MASCULINO" TO TEXSEXO
           ELSE
              IF SEXO = "F"
                 MOVE "FEMENINO" TO TEXSEXO
              ELSE
                 MOVE "*DIGITE M=MASCULINO    F=FEMININO*" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R5.
           DISPLAY TTEXSEXO.
       R6.
           MOVE 1 TO IND
           ACCEPT TGENERO.
       R6A.
           MOVE TBGENERO(IND) TO TXTGENERO
           IF TXTGENERO1 NOT = GENERO
              ADD 1 TO IND
              IF IND < 10
                 GO TO R6A
              ELSE
                 MOVE "*** TIPO GENERO INCORRETO***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R6
           ELSE
               DISPLAY TTXTGENERO2.

       R7.
           DISPLAY TELATA
           ACCEPT TTPAMIGO.
           IF TPAMIGO = 0 
                 MOVE "*** DIGITE APENAS DE 1 ATE 9 ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R7.
       R7A.
           MOVE TBAMIGO(TPAMIGO) TO TXTTPAMIGO
           DISPLAY TTXTTPAMIGO.
           DISPLAY TELA2.
       INC-OPC.
                MOVE "S" TO W-OPCAO
                DISPLAY (23, 40) "DADOS OK (S/N) : ".
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
                WRITE REGAMIGO
                IF ST-ERRO = "00" OR "02"
                      MOVE "*** DADOS GRAVADOS *** " TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO R1.
                IF ST-ERRO = "22"
                  MOVE "* AMIGO JA EXISTE,DADOS NAO GRAVADOS *" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO R1
                ELSE
                      MOVE "ERRO NA GRAVACAO DO ARQUIVO DE PRODUTO"
                                                       TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO ROT-FIM.

       ROT-FIM.
           CLOSE CADAMIGO.
           STOP RUN.

      *---------[ ROTINA DE MENSAGEM ]---------------------
       ROT-MENS.
                MOVE ZEROS TO W-CONT.
       ROT-MENS1.
               DISPLAY (23, 12) MENS.
       ROT-MENS2.
                ADD 1 TO W-CONT
                IF W-CONT < 3000
                   GO TO ROT-MENS2
                ELSE
                   MOVE SPACES TO MENS
                   DISPLAY (23, 12) MENS.
       ROT-MENS-FIM.
                EXIT.
       FIM-ROT-TEMPO.

      *    FILE STATUS
      *    00 = OPERA  O REALIZADO COM SUCESSO
      *    22 = REGISTRO J  CADASTRADO
      *    23 = REGISTRO N O ENCONTRADO
      *    30 = ARQUIVO N O ENCONTRADO