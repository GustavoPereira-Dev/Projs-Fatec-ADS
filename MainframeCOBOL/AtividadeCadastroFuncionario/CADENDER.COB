       IDENTIFICATION DIVISION.
       PROGRAM-ID. PROGRAMA02.
      *AUTHOR. GUSTAVO PEREIRA.
      ***************************************
      * MANUTENCAO DO CADASTRO DE ENDERECOS *
      ***************************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
             DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       SELECT CADENDR ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS CEP
                    FILE STATUS  IS ST-ERRO
      *
      *-----------------------------------------------------------------
       DATA DIVISION.
       FILE SECTION.
       FD CADENDR
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADENDR.DAT". 
       01 REGENDR.
          03 CEP           PIC 9(8)	  
          03 LOGRADOURO    PIC X(35)
          03 BAIRRO        PIC X(20)
          03 CIDADE        PIC X(20)
          03 UF            PIC X(2)
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
       01 IND           PIC 9(02) VALUE ZEROS.

       01 TABAMIGO.
          03 TBAMIGO    PIC X(10) OCCURS 9 TIMES.

       01 TABUFX.
          03 FILLER     PIC X(15) VALUE "ACACRE".
          03 FILLER     PIC X(15) VALUE "ALALAGOAS".
          03 FILLER     PIC X(15) VALUE "APAMAPÁ".
          03 FILLER     PIC X(15) VALUE "AMAMAZONAS".
          03 FILLER     PIC X(15) VALUE "BABAHIA".
          03 FILLER     PIC X(15) VALUE "CECEARÁ".
          03 FILLER     PIC X(15) VALUE "ESESPÍRITO SANTO".
          03 FILLER     PIC X(15) VALUE "GOGOIÁS".
          03 FILLER     PIC X(15) VALUE "MAMARANHÃO".
          03 FILLER     PIC X(15) VALUE "MTMATO GROSSO".
          03 FILLER     PIC X(15) VALUE "MSMATO GROSSO DO SUL".
          03 FILLER     PIC X(15) VALUE "MGMINAS GERAIS".
          03 FILLER     PIC X(15) VALUE "PAPARÁ".
          03 FILLER     PIC X(15) VALUE "PBPARAÍBA".
          03 FILLER     PIC X(15) VALUE "PRPARANÁ".
          03 FILLER     PIC X(15) VALUE "PEPERNAMBUCO".
          03 FILLER     PIC X(15) VALUE "PIPIAUÍ".
          03 FILLER     PIC X(15) VALUE "RJRIO DE JANEIRO".
          03 FILLER     PIC X(15) VALUE "RNRIO GRANDE DO NORTE".
          03 FILLER     PIC X(15) VALUE "RSRIO GRANDE DO SUL".
          03 FILLER     PIC X(15) VALUE "RORONDÔNIA".
          03 FILLER     PIC X(15) VALUE "RRRORAIMA".
          03 FILLER     PIC X(15) VALUE "SCSANTA CATARINA".
          03 FILLER     PIC X(15) VALUE "SPSÃO PAULO".
          03 FILLER     PIC X(15) VALUE "SESERGIPE".
          03 FILLER     PIC X(15) VALUE "TOTOCANTINS".
          03 FILLER     PIC X(15) VALUE "NDNÃO DECLARADO".
       01 TABUF REDEFINES TABUFX.
          03 TBUF   PIC X(22) OCCURS 27 TIMES.
       01 TXTUF.
          03 TXTUF1 PIC X(02) VALUE SPACES.
          03 TXTUF2 PIC X(20) VALUE SPACES. 

      *-----------------------------------------------------------------
       SCREEN SECTION.
       01  TELAENDERECO.
           05  BLANK SCREEN.
           05  LINE 01  COLUMN 01 
               VALUE  "                         ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 01  COLUMN 41 
               VALUE  "ÄÄÄ¿".
           05  LINE 02  COLUMN 01 
               VALUE  "                         ³CADASTRO ENDER".
           05  LINE 02  COLUMN 41 
               VALUE  "ECO³".
           05  LINE 03  COLUMN 01 
               VALUE  "                         ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 03  COLUMN 41 
               VALUE  "ÄÄÄÙ".
           05  LINE 04  COLUMN 01 
               VALUE  "ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ".
           05  LINE 04  COLUMN 41 
               VALUE  "ÍÍÍÍÍÍÍÍÍÍ» ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿".
           05  LINE 05  COLUMN 01 
               VALUE  "ºCEP        :".
           05  LINE 05  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 06  COLUMN 01 
               VALUE  "º".
           05  LINE 06  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 07  COLUMN 01 
               VALUE  "º".
           05  LINE 07  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 08  COLUMN 01 
               VALUE  "ºLOGRADOURO :".
           05  LINE 08  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 09  COLUMN 01 
               VALUE  "º".
           05  LINE 09  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 10  COLUMN 01 
               VALUE  "º".
           05  LINE 10  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 11  COLUMN 01 
               VALUE  "ºBAIRRO     :".
           05  LINE 11  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 12  COLUMN 01 
               VALUE  "º".
           05  LINE 12  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 13  COLUMN 01 
               VALUE  "º".
           05  LINE 13  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 14  COLUMN 01 
               VALUE  "ºCIDADE     :".
           05  LINE 14  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 15  COLUMN 01 
               VALUE  "º".
           05  LINE 15  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 16  COLUMN 01 
               VALUE  "º".
           05  LINE 16  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 17  COLUMN 01 
               VALUE  "ºUF         :".
           05  LINE 17  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 18  COLUMN 01 
               VALUE  "º".
           05  LINE 18  COLUMN 41 
               VALUE  "          º ³                          ³".
           05  LINE 19  COLUMN 01 
               VALUE  "ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ".
           05  LINE 19  COLUMN 41 
               VALUE  "ÍÍÍÍÍÍÍÍÍÍ¼ ³                          ³".
           05  LINE 20  COLUMN 01 
               VALUE  "ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 20  COLUMN 41 
               VALUE  "ÄÄÄÄÄÄÄÄÄÄ¿ ³                          ³".
           05  LINE 21  COLUMN 01 
               VALUE  "³MENSAGEM :".
           05  LINE 21  COLUMN 41 
               VALUE  "          ³ ³                          ³".
           05  LINE 22  COLUMN 01 
               VALUE  "³".
           05  LINE 22  COLUMN 41 
               VALUE  "          ³ ³                          ³".
           05  LINE 23  COLUMN 01 
               VALUE  "³".
           05  LINE 23  COLUMN 41 
               VALUE  "          ³ ³                          ³".
           05  LINE 24  COLUMN 01 
               VALUE  "ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ".
           05  LINE 24  COLUMN 41 
               VALUE  "ÄÄÄÄÄÄÄÄÄÄÙ ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ".
           05  TCEP
               LINE 05  COLUMN 15  PIC 9(08)
               USING  CEP
               HIGHLIGHT.
           05  TLOGRADOURO
               LINE 08  COLUMN 15  PIC X(35)
               USING  LOGRADOURO
               HIGHLIGHT.
           05  TBAIRRO
               LINE 11  COLUMN 15  PIC X(20)
               USING  BAIRRO
               HIGHLIGHT.
           05  TCIDADE
               LINE 14  COLUMN 15  PIC X(20)
               USING  CIDADE
               HIGHLIGHT.
           05  TUF
               LINE 17  COLUMN 15  PIC X(02)
               USING  UF
               HIGHLIGHT.
           05  TTXTUF
               LINE 17  COLUMN 18  PIC X(20)
               USING  TXTUF
               HIGHLIGHT.


      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
       
       R0.
           OPEN I-O CADENDR
           IF ST-ERRO NOT = "00"  
              IF ST-ERRO = "30"
                 OPEN OUTPUT CADENDR
                 CLOSE CADENDR
                 MOVE "*** ARQUIVO CADENDR FOI CRIADO **" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R0
              ELSE
                 MOVE "ERRO NA ABERTURA DO ARQUIVO CADENDR" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
                 NEXT SENTENCE.

      *   
            
       R1.
           MOVE SPACES TO LOGRADOURO BAIRRO CIDADE UF
           MOVE SPACES TO TXTUF
           MOVE ZEROS TO CEP
           DISPLAY TELAENDERECO.

       R2.
           ACCEPT TCEP
           IF CEP < 01000000
              MOVE "*** CEP INEXISTENTE ***" TO MENS
              PERFORM ROT-MENS THRU ROT-MENS-FIM
              GO TO R2.
           ACCEPT TLOGRADOURO.
    
       R3.
           ACCEPT TBAIRRO
           IF BAIRRO = ZEROS
              GO TO R3.
           ACCEPT CIDADE

       R4.
           MOVE 1 TO IND
           ACCEPT TUF.
       R4A.
           MOVE TBUF(IND) TO TXTUF
           IF TXTUF1 NOT = UF
              ADD 1 TO IND
              IF IND < 27
                 GO TO R4A
              ELSE
                 MOVE "*** UF INCORRETO ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R4
           ELSE
               DISPLAY TTXTUF2.

       INC-OPC.
                MOVE "S" TO W-OPCAO
                DISPLAY (23, 40) "DADOS OK (S/N) : ".
                ACCEPT (23, 57) W-OPCAO WITH UPDATE
                ACCEPT W-ACT FROM ESCAPE KEY
                IF W-ACT = 01 GO TO R4.
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** DADOS RECUSADOS PELO OPERADOR ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "*** DIGITE APENAS S=SIM e N=NAO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO INC-OPC.
       INC-WR1.
                WRITE REGENDR
                IF ST-ERRO = "00" OR "02"
                      MOVE "*** DADOS GRAVADOS *** " TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO R1.
                IF ST-ERRO = "22"
                  MOVE "* ENDERECO JA EXISTE,DADOS NAO GRAVADOS *" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO R1
                ELSE
                      MOVE "ERRO NA GRAVACAO DO ARQUIVO DE PRODUTO"
                                                       TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO ROT-FIM.

       ROT-FIM.

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
      *    00 = OPERAÇÃO REALIZADO COM SUCESSO
      *    22 = REGISTRO JÁ CADASTRADO
      *    30 = ARQUIVO NÃO ENCONTRADO
      