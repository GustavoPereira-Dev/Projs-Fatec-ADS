       IDENTIFICATION DIVISION.
       PROGRAM-ID. PROGCARGO.
      *AUTHOR. GUSTAVO PEREIRA.
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
	   01 CARGO-CODENTR		   PIC 9(5)	 VALUE ZEROS.
	   01 CARGO-DNMNCENTR	   PIC X(20) VALUE ZEROS.
      *-----------------------------------------------------------------
      *
      *************************
      * REPORT SECTION *
      *************************
      *
       01 DET2.
			   03 FILLER 			  PIC X(09) VALUE "CODIGO : ".	
               03 DETCARGO-COD        PIC 9(05).
			   03 FILLER 			  PIC X(14) VALUE "DENOMINACAO : ".
               03 DETCARGO-DNMNC      PIC X(20).
			   03 FILLER 			  PIC X(14) VALUE "CODIGO AREA : ".
               03 DETCARGO-AREA       PIC 9(3).
	   01 DET3.			   
			   03 FILLER 			  PIC X(15) VALUE "TIPO SALARIO : ".			   
               03 DETCARGO-TPSAL      PIC 9(1).
			   03 FILLER 			  PIC X(15) VALUE "SALARIO BASE : ".			   
               03 DETCARGO-SALBASE    PIC 9(6)V99.
			   
      *-----------------------------------------------------------------
       SCREEN SECTION.

       01  TCONSCARGO2.
           05  BLANK SCREEN.
           05  LINE 02  COLUMN 01 
               VALUE  "                           *** CONSULTA".
           05  LINE 02  COLUMN 41 
               VALUE  "CARGO ***".
           05  LINE 04  COLUMN 01 
               VALUE  "   CODIGO :        DENOMINACAO :".
           05  LINE 22  COLUMN 01 
               VALUE  "    MENSAGEM :".


      *-----------------------------------------------------------------
       PROCEDURE DIVISION.
       INICIO.
      *
       INC-OP1.
           OPEN INPUT  CADCARGO
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "30"
                 MOVE "ARQUIVO DE CADCARGO NAO EXISTE" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2
              ELSE
                 MOVE "ERRO ABERTURA DO ARQUIVO CADCARGO"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM2.
      *
       INC-001.
           MOVE ZEROS TO CARGO-COD
           MOVE SPACES TO CARGO-DNMNC
           DISPLAY  TCONSCARGO2.
       INC-001A.
           DISPLAY (23, 13)
            "SOLIC.=> F1=ENCERRA CONSULTA   F2=CONSULTA P/DENOMINACAO."
           ACCEPT TCARGO-CODENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADCARGO
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001B.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE CARGO-CODENTR TO CARGO-COD
           START CADCARGO KEY IS NOT LESS CARGO-COD INVALID KEY
                 MOVE "*** CHAVE NAO ENCONTRADA ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001.
           GO TO INC-RD2.
       INC-001B.
           DISPLAY (23, 13)
            "SOLIC.=> F1=ENCERRA CONSULTA     F2=CONSULTA P/CODIGO CARGO "
           ACCEPT TCARGO-DNMNCENTR
           ACCEPT W-ACT FROM ESCAPE KEY
           IF W-ACT = 02
                      CLOSE CADCARGO
                      GO TO ROT-FIM.
           IF W-ACT = 03
                      GO TO INC-001A.
           IF W-ACT > 03
                      MOVE "*** FUNCAO NAO DEFINIDA ***" TO MENS
                      PERFORM ROT-MENS THRU ROT-MENS-FIM
                      GO TO INC-001A.
           MOVE CARGO-DNMNCENTR TO CARGO-DNMNC
           START CADCARGO KEY IS NOT LESS CARGO-DNMNC INVALID KEY
                 MOVE "*** CHAVE NAO ENCONTRADA ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO INC-001B.
       INC-RD2.
           DISPLAY (23, 13) LIMPA
           READ CADCARGO NEXT
           IF ST-ERRO NOT = "00"
              IF ST-ERRO = "10"
                 MOVE "*** FIM DO ARQUIVO  ***" TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO NOV-SOL
              ELSE
                 MOVE "ERRO NA LEITURA DO ARQUIVO CADCARGO"  TO MENS
                 PERFORM ROT-MENS THRU ROT-MENS-FIM
                 GO TO ROT-FIM
           ELSE
              NEXT SENTENCE.
       ROT-MONTAR.
           MOVE CARGO-COD 		TO DETCARGO-COD
           MOVE CARGO-DNMNC    	TO DETCARGO-DNMNC
           MOVE CARGO-AREA   	TO DETCARGO-AREA
		   MOVE CARGO-TPSAL 	TO DETCARGO-TPSAL
		   MOVE CARGO-SALBASE   TO DETCARGO-SALBASE
		   
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 01) DET2
           ADD 1 TO CONLIN
           COMPUTE LIN = CONLIN + 3
           DISPLAY (LIN, 01) DET3
           ADD 2 TO CONLIN
           IF CONLIN < 17
                  GO TO INC-RD2.
      *
       ROT-SOL.
           DISPLAY (23, 13) " *** CONTINUA CONSULTA : . ***"
           MOVE "S" TO W-OPCAO
           ACCEPT  (23, 38) W-OPCAO  WITH UPDATE
           IF W-OPCAO = "S"
                  MOVE 01 TO CONLIN
                  DISPLAY TCONSCARGO2
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
           CLOSE CADCARGO.
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
           IF W-CONT < 1000
              GO TO ROT-MENS2
           ELSE
              DISPLAY (23, 12) LIMPA.
       ROT-MENS-FIM.
           EXIT.
       FIM-ROT-MENS.
      *

      *---------------------*** FIM DE PROGRAMA ***--------------------*	   