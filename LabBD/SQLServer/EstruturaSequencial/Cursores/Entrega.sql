CREATE DATABASE Entrega
USE Entrega

create table envio (
    CPF varchar(20),
    NR_LINHA_ARQUIV int,
    CD_FILIAL int,
    DT_ENVIO datetime,
    NR_DDD int,
    NR_TELEFONE varchar(10),
    NR_RAMAL varchar(10),
    DT_PROCESSAMENT datetime,
    NM_ENDERECO varchar(200),
    NR_ENDERECO int,
    NM_COMPLEMENTO varchar(50),
    NM_BAIRRO varchar(100),
    NR_CEP varchar(10),
    NM_CIDADE varchar(100),
    NM_UF varchar(2),
)

create table endereço(
CPF varchar(20),
CEP varchar(10),
PORTA int,
ENDEREÇO varchar(200),
COMPLEMENTO varchar(100),
BAIRRO varchar(100),
CIDADE varchar(100),
UF Varchar(2))

CREATE PROCEDURE sp_insereenvio
    as
    declare @cpf as int
    declare @cont1 as int
    declare @cont2 as int
    declare @conttotal as int
    set @cpf = 11111
    set @cont1 = 1
    set @cont2 = 1
    set @conttotal = 1
    while @cont1 <= @cont2 and @cont2 < = 100
    begin
    insert into envio (CPF, NR_LINHA_ARQUIV, DT_ENVIO)
    values (cast(@cpf as varchar(20)), @cont1,GETDATE())
    insert into endereço (CPF,PORTA,ENDEREÇO)
    values (@cpf,@conttotal,CAST(@cont2 as varchar(3))+'Rua '+CAST(@conttotal as varchar(5)))
    set @cont1 = @cont1 + 1
    set @conttotal = @conttotal + 1
    if @cont1 > = @cont2
    begin
    set @cont1 = 1
    set @cont2 = @cont2 + 1
    set @cpf = @cpf + 1
    end
    end
    exec sp_insereenvio
    select * from envio order by CPF,NR_LINHA_ARQUIV asc
    select * from endereço order by CPF asc

-- A empresa tinha duas tabelas: Envio e Endereço, como listada abaixo. No atributo NR_LINHA_ARQUIV, há
-- um número que faz referência à linha de incidência do endereço na tabela endereço.
-- Portanto, o NR_LINHA_ARQUIV (1) referencia o registro do endereço da Rua A e o NR_LINHA_ARQUIV (2)
-- referencia o endereço da Rua B.
-- Como se trata de uma estrutura completamente mal feita, o DBA solicitou que se colcoasse as colunas
-- NM_ENDERECO, NR_ENDERECO, NM_COMPLEMENTO, NM_BAIRRO, NR_CEP, NM_CIDADE, NM_UF
-- varchar(2) e movesse os dados da tabela endereço para a tabela envio.
-- Fazer uma PROCEDURE, com um cursor, que resolva esse problema

CREATE PROCEDURE sp_migrarenderecos
AS
BEGIN

    DECLARE @envio_CPF VARCHAR(20);
    DECLARE @envio_NR_LINHA INT;

    DECLARE @end_CEP VARCHAR(10),
            @end_PORTA INT,
            @end_ENDERECO VARCHAR(200),
            @end_COMPLEMENTO VARCHAR(100),
            @end_BAIRRO VARCHAR(100),
            @end_CIDADE VARCHAR(100),
            @end_UF VARCHAR(2);

    DECLARE cursor_envio CURSOR FOR
        SELECT CPF, NR_LINHA_ARQUIV
        FROM envio
        WHERE NM_ENDERECO IS NULL;

    OPEN cursor_envio;
    FETCH NEXT FROM cursor_envio INTO @envio_CPF, @envio_NR_LINHA;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        SELECT 
            @end_CEP = e1.CEP,
            @end_PORTA = e1.PORTA,
            @end_ENDERECO = e1.ENDEREÇO,
            @end_COMPLEMENTO = e1.COMPLEMENTO,
            @end_BAIRRO = e1.BAIRRO,
            @end_CIDADE = e1.CIDADE,
            @end_UF = e1.UF
        FROM 
            endereço e1
        WHERE 
            e1.CPF = @envio_CPF
            AND 
            (
                SELECT COUNT(*)
                FROM endereço e2
                WHERE e2.CPF = e1.CPF 
                  AND e2.PORTA <= e1.PORTA
            ) = @envio_NR_LINHA;

        IF @end_ENDERECO IS NOT NULL
        BEGIN
            UPDATE envio
            SET 
                NM_ENDERECO = @end_ENDERECO,
                NR_ENDERECO = @end_PORTA,
                NM_COMPLEMENTO = @end_COMPLEMENTO,
                NM_BAIRRO = @end_BAIRRO,
                NR_CEP = @end_CEP,
                NM_CIDADE = @end_CIDADE,
                NM_UF = @end_UF
            WHERE 
                CPF = @envio_CPF 
                AND NR_LINHA_ARQUIV = @envio_NR_LINHA;
        END

        SET @end_ENDERECO = NULL;
        FETCH NEXT FROM cursor_envio INTO @envio_CPF, @envio_NR_LINHA;
    END

    CLOSE cursor_envio;
    DEALLOCATE cursor_envio;

END
GO