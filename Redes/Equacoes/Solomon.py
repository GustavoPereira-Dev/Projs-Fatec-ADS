from reedsolomon.codec import RSCodec

print("### Demonstração Prática do Código Reed-Solomon com a Biblioteca `reedsolomon` ###")

# --- Parâmetros do Código Reed-Solomon ---
# nsym: Número de símbolos de paridade (parity symbols)
# Uma RSCodec(nsym) cria um código (255, 255-nsym), onde n é 255 por padrão
# e k = n - nsym é o número de símbolos de dados.
# Por exemplo, RSCodec(10) cria um código RS(255, 245), que pode corrigir
# até (10/2) = 5 símbolos de erro.
num_parity_symbols = 10 

# Cria uma instância do codificador RS. O n aqui é fixo em 255 para GF(2^8).
# O k (número de símbolos de dados) será 255 - num_parity_symbols.
rsc = RSCodec(num_parity_symbols)

# --- Mensagem de Exemplo ---
# A mensagem deve ser uma lista de inteiros, onde cada inteiro representa um símbolo (byte).
# O valor de cada símbolo deve estar entre 0 e 255 (para GF(2^8)).
# O comprimento da mensagem não pode exceder k (255 - num_parity_symbols).
message_bytes = bytearray(b"Ola, esta eh uma mensagem para codificacao Reed-Solomon.")

# Certifique-se de que a mensagem não é muito longa para o código RS(255, 255-nsym)
max_data_symbols = 255 - num_parity_symbols
if len(message_bytes) > max_data_symbols:
    print(f"A mensagem é muito longa. O máximo de símbolos de dados permitidos é {max_data_symbols}.")
    message_bytes = message_bytes[:max_data_symbols]

print(f"\nMensagem original (bytes): {message_bytes}")
print(f"Mensagem original (ASCII): {message_bytes.decode('utf-8')}")
print(f"Tamanho da mensagem: {len(message_bytes)} símbolos")
print(f"Número de símbolos de paridade configurados: {num_parity_symbols}")

# --- Codificação ---
# O método encode retorna a palavra-código completa (dados + paridade).
encoded_codeword = rsc.encode(message_bytes)

print(f"\nPalavra-código codificada (dados + paridade): {encoded_codeword}")
print(f"Tamanho da palavra-código: {len(encoded_codeword)} símbolos")

# --- Extraindo Paridade (Demonstração) ---
# Os últimos `num_parity_symbols` da palavra-código são os símbolos de paridade.
parity_symbols = encoded_codeword[len(message_bytes):]
print(f"Símbolos de paridade gerados: {parity_symbols}")

# --- Decodificação (Opcional, para demonstrar correção de erro) ---
# Para demonstrar a capacidade de correção de erro, vamos simular alguns erros.
# RSCodec(nsym) pode corrigir até nsym/2 erros.

# Criando uma versão com erro para demonstração
erroneous_codeword = bytearray(encoded_codeword)

# Simular 1 erro (se nsym >= 2)
if num_parity_symbols >= 2:
    error_position = 5 # Posição arbitrária
    original_value = erroneous_codeword[error_position]
    erroneous_codeword[error_position] = (original_value + 1) % 256 # Altera o byte
    print(f"\nSimulando 1 erro na posição {error_position}. Valor original: {hex(original_value)}, Novo valor: {hex(erroneous_codeword[error_position])}")
    
    try:
        decoded_message, errors_corrected = rsc.decode(erroneous_codeword)
        print(f"Mensagem decodificada após 1 erro: {decoded_message.decode('utf-8')}")
        print(f"Erros corrigidos: {errors_corrected}")
    except Exception as e:
        print(f"Erro na decodificação com 1 erro: {e}")

# Simular mais erros do que o código pode corrigir (se nsym >= 2)
if num_parity_symbols >= 2:
    too_many_errors_codeword = bytearray(encoded_codeword)
    for i in range(num_parity_symbols // 2 + 1): # Um erro a mais do que o limite
        pos = (10 + i) % len(too_many_errors_codeword) # Posições diferentes
        too_many_errors_codeword[pos] = (too_many_errors_codeword[pos] + i + 1) % 256
    print(f"\nSimulando {num_parity_symbols // 2 + 1} erros (excede a capacidade de correção).")
    try:
        decoded_message, errors_corrected = rsc.decode(too_many_errors_codeword)
        print(f"Mensagem decodificada (tentativa): {decoded_message.decode('utf-8')}")
        print(f"Erros corrigidos: {errors_corrected}")
    except Exception as e:
        print(f"Erro na decodificação com muitos erros: {e} (Esperado - o código não pode corrigir tantos erros)")

print("""
**Análise:**

Com a biblioteca `reedsolomon`, a complexidade da aritmética de campo de Galois é abstraída, permitindo uma codificação e decodificação diretas. O exemplo acima demonstra:
1.  **Criação do Codificador:** `RSCodec(num_parity_symbols)` inicializa um codificador Reed-Solomon. O número de símbolos de paridade (`nsym`) define a capacidade de correção de erros do código (`nsym / 2` erros).
2.  **Codificação:** O método `encode()` pega a mensagem (como um `bytearray` ou lista de inteiros) e retorna a palavra-código completa, que inclui os símbolos de dados originais mais os símbolos de paridade.
3.  **Decodificação:** O método `decode()` tenta reconstruir a mensagem original a partir de uma palavra-código potencialmente corrompida. Ele retorna a mensagem decodificada e o número de erros que foram corrigidos. Se o número de erros exceder a capacidade de correção do código, uma exceção é levantada, indicando que a decodificação falhou ou que a mensagem está irrecuperável.

Esta demonstração realça como bibliotecas especializadas lidam com a matemática complexa subjacente, tornando o uso de códigos de correção de erros mais acessível na prática.
""")