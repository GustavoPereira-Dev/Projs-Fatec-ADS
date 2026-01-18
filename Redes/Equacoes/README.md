# Equações

## Fourier.py

Este script Python demonstra a aproximação de uma onda quadrada utilizando a Série de Fourier. Ele permite visualizar como a soma de um número crescente de termos senoidais pode se aproximar de uma onda quadrada, destacando conceitos importantes como a convergência da série e o Fenômeno de Gibbs.

### Menu Interativo

O script apresenta um menu interativo para que o usuário possa escolher o número de termos a serem utilizados na aproximação da Série de Fourier:

```
--- Análise da Série de Fourier para Onda Quadrada ---

Escolha o número de termos para a aproximação (ou 'sair' para encerrar):
1. 1 termo
2. 5 termos
3. 20 termos
4. Custom (digite um número)
5. Sair
Sua escolha:
```

### Funcionalidades

*   **`square_wave(t, amplitude=1)`**: Define uma onda quadrada periódica.
*   **`fourier_series_square_wave(t, n_terms, amplitude=1)`**: Calcula a série de Fourier para uma onda quadrada, somando os `n_terms` ímpares necessários.
*   **`run_fourier_analysis()`**: Função principal que gerencia o menu interativo, coleta a entrada do usuário e plota a onda quadrada original junto com sua aproximação de Fourier.

### Exemplos de Uso e Análise

Ao executar a função `run_fourier_analysis()`, o usuário pode selecionar diferentes números de termos e observar os gráficos gerados. Abaixo, um exemplo de saída e análise dos resultados:

```python
# Exemplo de execução para 1, 5 e 20 termos
# Os gráficos seriam exibidos interativamente.
```

#### Saída e Análise

O código acima demonstra a aproximação de uma onda quadrada por sua série de Fourier, utilizando diferentes números de termos harmônicos. Os gráficos ilustram os seguintes pontos:

1.  **Convergência da Série:**
    *   Com apenas 1 termo, a aproximação é uma senoide simples, capturando apenas a frequência fundamental da onda quadrada.
    *   À medida que o número de termos aumenta para 5 e depois para 20, a forma de onda aproximada começa a se assemelhar cada vez mais à onda quadrada original. Isso ocorre porque termos de frequência mais alta são adicionados, que são essenciais para construir as "arestas" íngremes da onda quadrada.
2.  **Fenômeno de Gibbs:**
    *   Em todas as aproximações, mas mais visivelmente nos gráficos com mais termos (5 e 20), pode-se observar o "Fenômeno de Gibbs". Este é o overshoot e undershoot nos pontos de descontinuidade da onda quadrada (as transições abruptas entre -1 e 1).
    *   Embora a aproximação melhore com o aumento do número de termos, essa oscilação nos picos não desaparece completamente; ela se estreita, mas mantém uma amplitude quase constante, sendo uma característica inerente à aproximação de Fourier de funções com descontinuidades.

Em resumo, os gráficos mostram como a série de Fourier constrói funções complexas a partir de senoides e cossenos simples, e também destacam uma de suas limitações intrínsecas ao lidar com descontinuidades abruptas.


## Nyquist.py

Este script Python implementa o Teorema de Nyquist, um princípio fundamental no processamento de sinais que determina a taxa de amostragem mínima necessária para digitalizar um sinal analógico sem perda de informação.

### Menu Interativo

O script oferece um menu interativo para facilitar o cálculo da taxa de amostragem de Nyquist:

```
--- Ferramenta do Teorema de Nyquist ---

Escolha uma opção:
1. Calcular Taxa de Amostragem de Nyquist
2. Sair
Sua escolha:
```

### Funcionalidades

*   **`nyquist_sampling_rate(max_frequency)`**: Calcula a taxa de amostragem mínima necessária (frequência de Nyquist) com base na frequência mais alta presente no sinal. Garante que `max_frequency` não seja negativa, levantando um `ValueError` se for o caso.
*   **`run_nyquist_tool()`**: Função principal que gerencia o menu interativo, solicita a frequência máxima ao usuário e exibe a taxa de Nyquist calculada.

### Exemplo de Uso

Ao selecionar a opção `1` no menu e fornecer a frequência máxima, o script calculará e exibirá a taxa de amostragem mínima:

```
--- Ferramenta do Teorema de Nyquist ---

Escolha uma opção:
1. Calcular Taxa de Amostragem de Nyquist
2. Sair
Sua escolha: 1
Digite a frequência máxima do sinal em Hz (ex: 20000 para 20kHz): 20000
Para um sinal com frequência máxima de 20000.0 Hz, a taxa de amostragem mínima (Nyquist) é de 40000.0 Hz.
```

### Explicação do Teorema de Nyquist

O Teorema de Nyquist, também conhecido como teorema de amostragem de Nyquist-Shannon, é um princípio fundamental na teoria da informação e processamento de sinais. Ele estabelece que, para que um sinal analógico seja perfeitamente reconstruído a partir de uma sequência discreta de amostras, a taxa de amostragem deve ser de pelo menos duas vezes a frequência máxima do sinal original. Se a taxa de amostragem for menor que o dobro da frequência máxima, ocorrerá o fenômeno de aliasing, onde diferentes frequências se tornam indistinguíveis após a amostragem, levando à perda irreversível de informação.

## Shannon.py

Este script Python implementa o Teorema de Shannon-Hartley, um princípio fundamental na teoria da informação que calcula a capacidade máxima teórica de um canal de comunicação.

### Menu Interativo

O script oferece um menu interativo para facilitar o cálculo da Capacidade de Shannon:

```
--- Ferramenta do Teorema de Shannon-Hartley ---

Escolha uma opção:
1. Calcular Capacidade de Shannon
2. Sair
Sua escolha:
```

### Funcionalidades

*   **`shannon_hartley_capacity(bandwidth, snr_linear)`**: Calcula a capacidade máxima de um canal em bits por segundo (bps), com base na largura de banda (em Hz) e na relação sinal-ruído linear (SNR). A função valida as entradas para garantir que não sejam negativas.
*   **`run_shannon_hartley_tool()`**: Função principal que gerencia o menu interativo, solicita ao usuário a largura de banda e a SNR (em dB, que é convertida para linear) e exibe a capacidade de Shannon calculada.

### Exemplo de Uso

Ao selecionar a opção `1` no menu e fornecer a largura de banda e a SNR, o script calculará e exibirá a capacidade máxima teórica do canal:

```
--- Ferramenta do Teorema de Shannon-Hartley ---

Escolha uma opção:
1. Calcular Capacidade de Shannon
2. Sair
Sua escolha: 1
Digite a largura de banda do canal em Hz (ex: 3100 para canal telefônico): 3100
Digite a relação sinal-ruído (SNR) em dB (ex: 30 para canal telefônico): 30
Para Largura de Banda de 3100.0 Hz e SNR de 30.0 dB (1000.00 linear):
A capacidade máxima teórica do canal é de 30896.79 bps.
```

### Explicação do Teorema de Shannon-Hartley

O Teorema de Shannon-Hartley, desenvolvido por Claude Shannon, é um dos pilares da teoria da informação. Ele estabelece o limite superior teórico da taxa de dados que pode ser transmitida de forma confiável (com uma taxa de erro arbitrariamente pequena) por um canal de comunicação de banda limitada, sujeito a ruído gaussiano aditivo (AWGN).

A fórmula é: `C = B * log2(1 + SNR)`
Onde:
- `C` é a Capacidade do Canal em bits por segundo (bps).
- `B` é a Largura de Banda do canal em Hertz (Hz).
- `SNR` é a Relação Sinal-Ruído linear (não em decibéis).

Este teorema é crucial porque define o máximo que se pode alcançar em termos de velocidade de comunicação sob condições ideais para um dado canal, independentemente da tecnologia de modulação ou codificação utilizada. Ele mostra que a largura de banda e a qualidade do sinal (medida pela SNR) são os fatores fundamentais que limitam o desempenho da comunicação.

## Hamming.py

Este script Python calcula a Distância de Hamming entre duas strings binárias de mesmo comprimento. A Distância de Hamming é o número de posições nas quais os símbolos correspondentes são diferentes, sendo uma métrica fundamental em teoria da informação e códigos de correção de erros.

### Menu Interativo

O script oferece um menu interativo para facilitar o cálculo da Distância de Hamming:

```
--- Ferramenta de Cálculo de Distância de Hamming ---

Escolha uma opção:
1. Calcular Distância de Hamming
2. Sair
Sua escolha:
```

### Funcionalidades

*   **`hamming_distance(binary_string1, binary_string2)`**: Calcula a Distância de Hamming. A função valida se as strings são binárias e se possuem o mesmo comprimento, levantando um `ValueError` em caso de erro.
*   **`run_hamming_tool()`**: Função principal que gerencia o menu interativo, solicita ao usuário duas strings binárias e exibe a distância de Hamming calculada.

### Exemplo de Uso

Ao selecionar a opção `1` no menu e fornecer duas strings binárias, o script calculará e exibirá a Distância de Hamming:

```
--- Ferramenta de Cálculo de Distância de Hamming ---

Escolha uma opção:
1. Calcular Distância de Hamming
2. Sair
Sua escolha: 1
Digite a primeira string binária (ex: 1011001): 1011001
Digite a segunda string binária (ex: 1010001): 1010001
Strings: '1011001', '1010001'
Distância de Hamming: 1
```

**Exemplo com erro (strings de comprimentos diferentes):**

```
--- Ferramenta de Cálculo de Distância de Hamming ---

Escolha uma opção:
1. Calcular Distância de Hamming
2. Sair
Sua escolha: 1
Digite a primeira string binária (ex: 1011001): 101
Digite a segunda string binária (ex: 1010001): 1010
Erro: Input strings must have the same length. Por favor, verifique as strings digitadas.
```

## Convolutional.py

Este script Python implementa uma versão simplificada de codificação convolucional, um tipo de código de correção de erros amplamente utilizado em telecomunicações para melhorar a confiabilidade da transmissão de dados.

### Menu Interativo

O script oferece um menu interativo para facilitar a demonstração da codificação convolucional:

```
--- Ferramenta de Codificação Convolucional Simplificada ---

Escolha uma opção:
1. Realizar Codificação Convolucional
2. Sair
Sua escolha:
```

### Funcionalidades

*   **`convolutional_encode(message_bits, generator_polynomials, initial_state=None)`**: Realiza a codificação convolucional de uma mensagem binária usando um registrador de deslocamento e polinômios geradores. A função valida as entradas (mensagens e polinômios binários, comprimento consistente dos polinômios e estado inicial). Os bits de saída são gerados através de operações XOR baseadas nos polinômios geradores e no estado atual do registrador.
*   **`run_convolutional_tool()`**: Função principal que gerencia o menu interativo, solicita a mensagem binária, os polinômios geradores e opcionalmente um estado inicial, e exibe a sequência de bits codificada.

### Exemplo de Uso

Ao selecionar a opção `1` no menu e fornecer as informações solicitadas, o script realizará a codificação:

```
--- Ferramenta de Codificação Convolucional Simplificada ---

Escolha uma opção:
1. Realizar Codificação Convolucional
2. Sair
Sua escolha: 1
Digite a mensagem binária (ex: 1011): 1011
Quantos polinômios geradores você tem? 2
Digite o polinômio gerador 1 (binário, ex: 1101): 1101
Digite o polinômio gerador 2 (binário, ex: 1111): 1111
Deseja especificar um estado inicial para o registrador de deslocamento? (s/n, padrão 'n'): n

Mensagem Original: 1011
Polinômios Geradores: ['1101', '1111']
Saída Codificada: 11111011101011
```

### Explicação

A codificação convolucional é um método de adição de redundância a uma mensagem de dados para protegê-la contra erros que podem ocorrer durante a transmissão. Ela opera processando a mensagem de entrada em pequenos blocos (bits), e a saída codificada para cada bit de entrada depende não apenas do bit atual, mas também de um histórico de bits anteriores armazenados em um "registrador de deslocamento" (shift register). Os "polinômios geradores" definem como os bits no registrador de deslocamento são combinados (usando operações XOR) para produzir os bits de saída.

Esta implementação simplificada demonstra o processo de codificação, mas não inclui a parte de decodificação (que é significativamente mais complexa e geralmente envolve algoritmos como Viterbi).

## Reed-Solomon Code (Demonstração Conceitual)

Este script Python oferece uma demonstração conceitual do funcionamento do código Reed-Solomon, utilizando a biblioteca `reedsolomon` para ilustrar a codificação e a correção de erros. Ele destaca a complexidade subjacente à aritmética de campo de Galois, que é abstraída pelas bibliotecas especializadas.

### Funcionalidades

*   **Configuração do Codificador**: Utiliza `RSCodec(num_parity_symbols)` para criar uma instância do codificador Reed-Solomon. O `num_parity_symbols` (número de símbolos de paridade) define a capacidade de correção de erros do código, que é de `nsym / 2` erros.
*   **Codificação**: O método `encode()` da instância `RSCodec` recebe uma mensagem (como `bytearray` ou lista de inteiros) e retorna a palavra-código completa, que inclui os dados originais e os símbolos de paridade gerados.
*   **Simulação de Erros**: Demonstra como introduzir erros em uma palavra-código e tenta decodificá-la para mostrar a capacidade de correção de erros do código.
*   **Decodificação**: O método `decode()` tenta reconstruir a mensagem original a partir de uma palavra-código potencialmente corrompida. Ele retorna a mensagem decodificada e o número de erros corrigidos. Uma exceção é levantada se o número de erros exceder a capacidade de correção do código.

### Exemplo de Uso

```python
from reedsolomon.codec import RSCodec

# --- Parâmetros do Código Reed-Solomon ---
num_parity_symbols = 10 # Capacidade de corrigir até 5 erros
rsc = RSCodec(num_parity_symbols)

# --- Mensagem de Exemplo ---
message_bytes = bytearray(b"Ola, esta eh uma mensagem para codificacao Reed-Solomon.")
print(f"\nMensagem original (bytes): {message_bytes}")
print(f"Mensagem original (ASCII): {message_bytes.decode('utf-8')}")

# --- Codificação ---
encoded_codeword = rsc.encode(message_bytes)
print(f"\nPalavra-código codificada (dados + paridade): {encoded_codeword}")
print(f"Tamanho da palavra-código: {len(encoded_codeword)} símbolos")

# --- Decodificação (com 1 erro) ---
erroneous_codeword = bytearray(encoded_codeword)
error_position = 5
original_value = erroneous_codeword[error_position]
erroneous_codeword[error_position] = (original_value + 1) % 256 # Altera o byte
print(f"\nSimulando 1 erro na posição {error_position}. Valor original: {hex(original_value)}, Novo valor: {hex(erroneous_codeword[error_position])}")

try:
    decoded_message, errors_corrected = rsc.decode(erroneous_codeword)
    print(f"Mensagem decodificada após 1 erro: {decoded_message.decode('utf-8')}")
    print(f"Erros corrigidos: {errors_corrected}")
except Exception as e:
    print(f"Erro na decodificação com 1 erro: {e}")

# --- Decodificação (com muitos erros) ---
# Simular mais erros do que o código pode corrigir (se nsym >= 2)
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
```

### Análise

Com a biblioteca `reedsolomon`, a complexidade da aritmética de campo de Galois é abstraída, permitindo uma codificação e decodificação diretas. O exemplo demonstra:
1.  **Criação do Codificador:** `RSCodec(num_parity_symbols)` inicializa um codificador Reed-Solomon. O número de símbolos de paridade (`nsym`) define a capacidade de correção de erros do código (`nsym / 2` erros).
2.  **Codificação:** O método `encode()` pega a mensagem (como um `bytearray` ou lista de inteiros) e retorna a palavra-código completa, que inclui os símbolos de dados originais mais os símbolos de paridade.
3.  **Decodificação:** O método `decode()` tenta reconstruir a mensagem original a partir de uma palavra-código potencialmente corrompida. Ele retorna a mensagem decodificada e o número de erros que foram corrigidos. Se o número de erros exceder a capacidade de correção do código, uma exceção é levantada, indicando que a decodificação falhou ou que a mensagem está irrecuperável.

Esta demonstração realça como bibliotecas especializadas lidam com a matemática complexa subjacente, tornando o uso de códigos de correção de erros mais acessível na prática.

## Checksum.py

Este script Python implementa o cálculo do Internet Checksum de 16 bits, um método simples de verificação de erros usado em protocolos de rede como IP, TCP e UDP para garantir a integridade dos dados durante a transmissão.

### Menu Interativo

O script oferece um menu interativo para facilitar o cálculo do checksum:

```
--- Ferramenta de Cálculo de Checksum de Redes ---

Escolha uma opção:
1. Calcular Checksum
2. Sair
Sua escolha:
```

### Funcionalidades

*   **`calculate_internet_checksum(data_bytes)`**: Calcula o checksum de 16 bits para um objeto `bytes` fornecido. Ele soma as palavras de 16 bits dos dados, lida com os "carries" (transportes) que extrapolam os 16 bits (somando-os de volta ao resultado) e, finalmente, tira o complemento de um do resultado final. Se o número de bytes for ímpar, um byte zero é adicionado para preenchimento.
*   **`run_checksum_tool()`**: Função principal que gerencia o menu interativo. Ela solicita ao usuário dados em formato hexadecimal, converte-os para `bytes` e exibe o checksum calculado. Inclui validação para garantir que a entrada seja hexadecimal válida.

### Exemplo de Uso

Ao selecionar a opção `1` no menu e fornecer dados em hexadecimal, o script calculará e exibirá o checksum:

```
--- Ferramenta de Cálculo de Checksum de Redes ---

Escolha uma opção:
1. Calcular Checksum
2. Sair
Sua escolha: 1
Digite os dados em formato hexadecimal (ex: C0A801010001): C0A801010001
Dados de Entrada (Hex): C0A801010001
Dados de Entrada (Bytes): b'\xc0\xa8\x01\x01\x00\x01'
Checksum Calculado: 0x54F7
```

**Exemplo com entrada de comprimento ímpar (o script preenche com '0' à esquerda):**

```
--- Ferramenta de Cálculo de Checksum de Redes ---

Escolha uma opção:
1. Calcular Checksum
2. Sair
Sua escolha: 1
Digite os dados em formato hexadecimal (ex: C0A801010001): 123
Dados de Entrada (Hex): 0123
Dados de Entrada (Bytes): b'\x01#'
Checksum Calculado: 0xFFDC
```

### Explicação do Checksum de Redes

O Internet Checksum é um algoritmo de soma de verificação simples projetado para detectar erros acidentais em dados transmitidos, como aqueles que podem ocorrer devido a ruído em um canal de comunicação. Ele não é robusto o suficiente para proteger contra ataques maliciosos ou grandes volumes de erros, mas é eficaz para detectar a maioria dos erros aleatórios de bit único e bit duplo. A ideia principal é que a soma de todos os bits de 16 em 16 (considerando o complemento de um) de um cabeçalho IP (ou segmento TCP/UDP) deve ser 0 quando o checksum for adicionado, indicando que o pacote não foi corrompido durante a transmissão.