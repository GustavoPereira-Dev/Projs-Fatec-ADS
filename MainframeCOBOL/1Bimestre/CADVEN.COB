       IDENTIFICATION DIVISION.
       PROGRAM-ID. PROJETOA.
      *AUTHOR. GUSTAVO PEREIRA.
      **************************************
      *       CADASTRO DE VEICULOS         *
      **************************************
      *----------------------------------------------------------------
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SPECIAL-NAMES.
             DECIMAL-POINT IS COMMA.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       SELECT CADCAR ASSIGN TO DISK
                    ORGANIZATION IS INDEXED
                    ACCESS MODE  IS DYNAMIC
                    RECORD KEY   IS PLACA
                    FILE STATUS  IS ST-ERRO.

      *
      *-----------------------------------------------------------------  
       DATA DIVISION.
       FILE SECTION.
       FD CADCAR
               LABEL RECORD IS STANDARD
               VALUE OF FILE-ID IS "CADCAR.DAT". 
       01 REGCAR.
          03 PLACA         PIC X(7).
          03 NOMEPROP      PIC X(30).
          03 MARCA         PIC X(2).
          03 ANOFABC       PIC 9(4).
          03 COR           PIC 9(1).
      *
      *-----------------------------------------------------------------     
       WORKING-STORAGE SECTION.
       77 W-CONT        PIC 9(06) VALUE ZEROS.
       77 W-OPCAO       PIC X(01) VALUE SPACES.
       77 W-ACT         PIC 9(02) VALUE ZEROS.
       77 MENS          PIC X(40) VALUE SPACES.
       77 LIMPA         PIC X(40) VALUE SPACES. 
       01 ST-ERRO       PIC X(02) VALUE "00".
       01 W-SEL         PIC 9(01) VALUE ZEROS.
       01 TXTCOR        PIC X(15) VALUE ZEROS.
       01 TXTTCOR       PIC X(15) VALUE SPACES.
       01 IND           PIC 9(02) VALUE ZEROS.
       01 TEXSEXO       PIC X(12) VALUE SPACES.

       01 TABCOR.
          03 TBCOR    PIC X(10) OCCURS 10 TIMES.

       01 TABMARCAX.
          03 FILLER     PIC X(15) VALUE "VWVOLSKWAGEN".
          03 FILLER     PIC X(15) VALUE "GMGENERAL MOTOR".
          03 FILLER     PIC X(15) VALUE "TOTOYOTA".
          03 FILLER     PIC X(15) VALUE "FTFIAT".
          03 FILLER     PIC X(15) VALUE "CTCITROEM".
          03 FILLER     PIC X(15) VALUE "SBSUBARU".
          03 FILLER     PIC X(15) VALUE "VVVOLVO".
          03 FILLER     PIC X(15) VALUE "HYHYUNDAI".
          03 FILLER     PIC X(15) VALUE "FDFORD".
          03 FILLER     PIC X(15) VALUE "FRFERRARI".
	  03 FILLER     PIC X(15) VALUE "CHCHERY".
          03 FILLER     PIC X(15) VALUE "BWBMW".
          03 FILLER     PIC X(15) VALUE "NDNAO DECLARADO".
       01 TABMARCA REDEFINES TABMARCAX.
          03 TBMARCA   PIC X(15) OCCURS 13 TIMES.
       01 TXTMARCA.
          03 TXTMARCA1 PIC X(02) VALUE SPACES.
          03 TXTMARCA2 PIC X(13) VALUE SPACES. 



      *-----------------------------------------------------------------
       SCREEN SECTION. 
       01  TELAVEICULO.
	   05  BLANK SCREEN.
           05  LINE 01  COLUMN 01 
               VALUE  "                                        ".
           05  LINE 01  COLUMN 41 
               VALUE  "     Ŀ".
           05  LINE 02  COLUMN 01 
               VALUE  "                         CADASTRO DE VEI".
           05  LINE 02  COLUMN 41 
               VALUE  "CULOS  ".
           05  LINE 03  COLUMN 01 
               VALUE  "                                        ".
           05  LINE 03  COLUMN 41 
               VALUE  "       ".
           05  LINE 04  COLUMN 01 
               VALUE  "                                        ".
           05  LINE 04  COLUMN 41 
               VALUE  "             ͻ                      Ŀ".
           05  LINE 05  COLUMN 01 
               VALUE  "   PLACA             :".
           05  LINE 05  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 06  COLUMN 01 
               VALUE  "  ".
           05  LINE 06  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 07  COLUMN 01 
               VALUE  "  ".
           05  LINE 07  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 08  COLUMN 01 
               VALUE  "   NOME PROPRIETARIO :".
           05  LINE 08  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 09  COLUMN 01 
               VALUE  "  ".
           05  LINE 09  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 10  COLUMN 01 
               VALUE  "  ".
           05  LINE 10  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 11  COLUMN 01 
               VALUE  "   MARCA             :".
           05  LINE 11  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 12  COLUMN 01 
               VALUE  "  ".
           05  LINE 12  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 13  COLUMN 01 
               VALUE  "  ".
           05  LINE 13  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 14  COLUMN 01 
               VALUE  "   ANO FABRICACAO    :".
           05  LINE 14  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 15  COLUMN 01 
               VALUE  "  ".
           05  LINE 15  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 16  COLUMN 01 
               VALUE  "  ".
           05  LINE 16  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 17  COLUMN 01 
               VALUE  "   COR               :".
           05  LINE 17  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 18  COLUMN 01 
               VALUE  "  ".
           05  LINE 18  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 19  COLUMN 01 
               VALUE  "                                        ".
           05  LINE 19  COLUMN 41 
               VALUE  "             ͼ                        ".
           05  LINE 20  COLUMN 01 
               VALUE  "                                        ".
           05  LINE 20  COLUMN 41 
               VALUE  "            Ŀ                         ".
           05  LINE 21  COLUMN 01 
               VALUE  " ".
           05  LINE 21  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 22  COLUMN 01 
               VALUE  " MENSAGEM :".
           05  LINE 22  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 23  COLUMN 01 
               VALUE  " ".
           05  LINE 23  COLUMN 41 
               VALUE  "                                       ".
           05  LINE 24  COLUMN 01 
               VALUE  "                                        ".
           05  LINE 24  COLUMN 41 
               VALUE  "                                       ".
           05  TPLACA
               LINE 05  COLUMN 24  PIC X(07)
               USING  PLACA
               HIGHLIGHT.
           05  TNOMEPROP
               LINE 08  COLUMN 24  PIC X(30)
               USING  NOMEPROP
               HIGHLIGHT.
           05  TMARCA
               LINE 11  COLUMN 24  PIC X(02)
               USING  MARCA
               HIGHLIGHT.
           05  TTXTMARCA2
               LINE 11  COLUMN 27  PIC X(15)
               USING  TXTMARCA
               HIGHLIGHT.
           05  TANOFABC
               LINE 14  COLUMN 24  PIC 9(04)
               USING  ANOFABC
               HIGHLIGHT.
           05  TCOR
               LINE 17  COLUMN 24  PIC 9(01)
               USING  COR
               HIGHLIGHT.
           05  TTXTCOR
               LINE 17  COLUMN 27  PIC X(15)
               USING  TXTCOR
               HIGHLIGHT.
 
      *
       01  TELACOR.
           05  LINE 10  COLUMN  63 VALUE  "0-CINZA".
           05  LINE 11  COLUMN 63 VALUE  "1-BRANCO".
           05  LINE 12  COLUMN 63 VALUE  "2-AZUL".
           05  LINE 13  COLUMN 63 VALUE  "3-VERMELHO".
           05  LINE 14  COLUMN 63 VALUE  "4-PRATA".
           05  LINE 15  COLUMN 63 VALUE  "5-AMARELO".
           05  LINE 16  COLUMN 63 VALUE  "6-LARANJA".
           05  LINE 17  COLUMN 63 VALUE  "7-ROSA".
           05  LINE 18  COLUMN 63 VALUE  "8-VERDE".
	   05  LINE 18  COLUMN 63 VALUE  "9-ROXO".
      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
	   MOVE "CINZA"    TO TBCOR(1)
           MOVE "BRANCO"   TO TBCOR(2)
           MOVE "AZUL"     TO TBCOR(3)
           MOVE "VERMELHO" TO TBCOR(4)
           MOVE "PRATA"    TO TBCOR(5)
           MOVE "AMARELO"  TO TBCOR(6)
           MOVE "LARANJA"  TO TBCOR(7)
           MOVE "ROSA"     TO TBCOR(8)
           MOVE "VERDE"    TO TBCOR(9)
	   MOVE "ROXO"     TO TBCOR(10).

       R0.	
           OPEN I-O CADCAR
           IF ST-ERRO NOT = "00"  
              IF ST-ERRO = "30"
                 OPEN OUTPUT CADCAR
                 CLOSE CADCAR
                 MOVE "*** ARQUIVO CADCAR FOI CRIADO **" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R0
              ELSE
                 MOVE "ERRO NA ABERTURA DO ARQUIVO CADCAR" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
                 NEXT SENTENCE.
      * 
      *------------[ INICIALIZACAO DAS VARIAVEIS ]---------------------         
       R1.
           MOVE SPACES TO PLACA NOMEPROP MARCA
           MOVE SPACES TO TXTMARCA
	   MOVE ZEROS TO ANOFABC COR W-SEL.
      *-------------[VISUALIZACAO DA TELA]--------------------------------  
           DISPLAY TELAVEICULO.
      
       R2.
           ACCEPT PLACA
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO ROT-FIM.
       LER-CADCAR.
           READ CADCAR
           IF ST-ERRO NOT = "23"
             IF ST-ERRO = "00"
                PERFORM R5A
                PERFORM R7A
                DISPLAY TELAVEICULO
                MOVE "*** CARRO JA CADASTRAD0 ***" TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ACE-001
             ELSE
                MOVE "ERRO NA LEITURA ARQUIVO CADCAR" TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM
           ELSE
                NEXT SENTENCE.

       R3.       
           ACCEPT TPLACA
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R2.
	   IF PLACA = SPACES
 	    	   GO TO R3.

       R4.       
           ACCEPT TNOMEPROP
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R2.
	   IF NOMEPROP = SPACES
 	    	   GO TO R4.
       R5.
           MOVE 0 TO IND
           ACCEPT TMARCA.
	   IF MARCA = SPACES
 	    	   GO TO R5.
       R5A.
           MOVE TBMARCA(IND) TO TXTMARCA
           IF TXTMARCA1 NOT = MARCA
              ADD 1 TO IND
              IF IND < 14
                 GO TO R5A
              ELSE
                 MOVE "*** TIPO MARCA INCORRETO***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO R5
           ELSE
               DISPLAY TXTMARCA2.
	
       R6.       
           ACCEPT TANOFABC
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 01
                   GO TO R2.
	   IF ANOFABC = ZEROS
 	    	   GO TO R6.

       R7.
           DISPLAY TELACOR
           ACCEPT TCOR.

       R7A.
           MOVE TBCOR(COR + 1) TO TXTCOR
           DISPLAY TTXTCOR.
	   DISPLAY TELAVEICULO.
      * ------------- VERICAR SE E ALTERACAO -----------------
           IF W-SEL = 1 
                GO TO ALT-OPC.
       INC-OPC.
                MOVE "S" TO W-OPCAO
                DISPLAY (23, 35) "DADOS OK (S/N) : ".
                ACCEPT (23, 54) W-OPCAO WITH UPDATE
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
                WRITE REGCAR
                IF ST-ERRO = "00" OR "02"
                      MOVE "*** DADOS GRAVADOS *** " TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO R1.
                IF ST-ERRO = "22"
                  MOVE "* CARRO JA EXISTE,DADOS NAO GRAVADOS *" TO MENS
                  PERFORM ROT-MENS THRU ROT-MENS-FIM
                  GO TO R1
                ELSE
                      MOVE "ERRO NA GRAVACAO DO ARQUIVO DE CARRO" 
						       TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO ROT-FIM.

      *
      *****************************************
      * ROTINA DE CONSULTA/ALTERACAO/EXCLUSAO *
      *****************************************
      *
       ACE-001.
                DISPLAY (23, 10)
                     "N=NOVO REGISTRO   A=ALTERAR   E=EXCLUIR"
                ACCEPT (23, 53) W-OPCAO
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
                DISPLAY (23, 35) "EXCLUIR   (S/N) : ".
                ACCEPT (23, 53) W-OPCAO
                IF W-OPCAO = "N" OR "n"
                   MOVE "*** REGISTRO NAO EXCLUIDO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                IF W-OPCAO NOT = "S" AND "s"
                   MOVE "* DIGITE APENAS S=SIM  e  N=NAO *" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO EXC-OPC.
       EXC-DL1.
                DELETE CADCAR RECORD
                IF ST-ERRO = "00"
                   MOVE "*** REGISTRO CARRO EXCLUIDO ***" TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                MOVE "ERRO NA EXCLUSAO DO REGISTRO "   TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM.
      *
       ALT-OPC.
                DISPLAY (23, 36) "ALTERAR  (S/N) : ".
                ACCEPT (23, 53) W-OPCAO
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
                REWRITE REGCAR
                IF ST-ERRO = "00" OR "02"
                   MOVE "*** REGISTRO ALTERADO ***         " TO MENS
                   PERFORM ROT-MENS THRU ROT-MENS-FIM
                   GO TO R1.
                MOVE "ERRO NA EXCLUSAO DO REGISTRO CARRO"   TO MENS
                PERFORM ROT-MENS THRU ROT-MENS-FIM
                GO TO ROT-FIM.
      *-------------------------------------------------------------------------------------------

       ROT-FIM.
           CLOSE CADCAR.
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