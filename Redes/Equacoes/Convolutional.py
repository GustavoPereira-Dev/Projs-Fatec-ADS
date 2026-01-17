def convolutional_encode(message_bits, generator_polynomials, initial_state=None):
    """
    Performs simplified convolutional encoding.

    Args:
        message_bits (str): A string of '0's and '1's representing the input message.
        generator_polynomials (list): A list of strings, each representing a generator
                                     polynomial in binary form (e.g., ['1101', '1111']).
        initial_state (str, optional): The initial state of the shift register. Defaults to
                                       '0's with length equal to len(generator_polynomials[0]) - 1.

    Returns:
        str: The encoded bit stream.

    Raises:
        ValueError: If input validation fails.
    """
    # 2. Input Validation
    if not all(c in '01' for c in message_bits):
        raise ValueError("message_bits must contain only '0's and '1's.")

    if not isinstance(generator_polynomials, list) or not generator_polynomials:
        raise ValueError("generator_polynomials must be a non-empty list.")

    poly_length = len(generator_polynomials[0])
    for poly in generator_polynomials:
        if not all(c in '01' for c in poly):
            raise ValueError(f"Generator polynomial '{poly}' must contain only '0's and '1's.")
        if len(poly) != poly_length:
            raise ValueError("All generator polynomials must have the same length.")

    num_memory_elements = poly_length - 1

    if initial_state is None:
        initial_state = '0' * num_memory_elements
    else:
        if not all(c in '01' for c in initial_state):
            raise ValueError("initial_state must contain only '0's and '1's.")
        if len(initial_state) != num_memory_elements:
            raise ValueError(f"initial_state length ({len(initial_state)}) must match the number of memory elements ({num_memory_elements}).")

    # 3. Initialize shift register
    # Convert initial_state string to a list of ints, most significant bit first
    shift_register = [int(b) for b in initial_state]

    encoded_bits = []

    # Add dummy zeros to message_bits for flushing the shift register
    message_bits_with_flush = message_bits + '0' * num_memory_elements

    # 5. Iterate through each bit in message_bits_with_flush
    for input_bit_char in message_bits_with_flush:
        input_bit = int(input_bit_char)

        # a. Prepend the current bit to the shift register
        shift_register.insert(0, input_bit)
        # Keep the register length correct by removing the oldest bit if it grew too large
        if len(shift_register) > poly_length: # poly_length is 1 (input) + num_memory_elements
            shift_register.pop() # Remove the oldest bit

        current_output_bits = []
        for poly_str in generator_polynomials:
            output_bit = 0
            for i in range(poly_length):
                # i represents the position in the generator polynomial, from left to right
                # Corresponding element in shift_register is also from left to right (most recent to oldest)
                if poly_str[i] == '1':
                    output_bit ^= shift_register[i] # XOR operation
            current_output_bits.append(output_bit)
        
        encoded_bits.extend(current_output_bits)

    # 7. Convert the encoded_bits (list of integers) back into a single binary string.
    return ''.join(map(str, encoded_bits))

# --- Example Usage ---
print("### Simplified Convolutional Encoding ###")

# Example 1: Basic encoding
message_1 = '1011'
generator_polynomials_1 = ['1101', '1111'] # G1 = 1+X^1+X^3, G2 = 1+X^1+X^2+X^3
# Number of memory elements = len(poly) - 1 = 4 - 1 = 3
# Initial state will be '000'

try:
    encoded_output_1 = convolutional_encode(message_1, generator_polynomials_1)
    print(f"\nMessage: {message_1}")
    print(f"Generator Polynomials: {generator_polynomials_1}")
    print(f"Encoded Output: {encoded_output_1}")
    # Expected output for message '1011' and initial state '000' (G1=1101, G2=1111):
    # Input: 1
    # Reg: [1,0,0,0]
    # G1: 1*1^0+0*1^1+0*0^2+0*1^3 = 1 -> 1
    # G2: 1*1^0+0*1^1+0*1^2+0*1^3 = 1 -> 1
    # Output: 11

    # Input: 0
    # Reg: [0,1,0,0]
    # G1: 0*1+1*1+0*0+0*1 = 1 -> 1
    # G2: 0*1+1*1+0*1+0*1 = 1 -> 1
    # Output: 11

    # Input: 1
    # Reg: [1,0,1,0]
    # G1: 1*1+0*1+1*0+0*1 = 1 -> 1
    # G2: 1*1+0*1+1*1+0*1 = 0 -> 0
    # Output: 10

    # Input: 1
    # Reg: [1,1,0,1]
    # G1: 1*1+1*1+0*0+1*1 = 1 -> 1
    # G2: 1*1+1*1+0*1+1*1 = 1 -> 1
    # Output: 11

    # Flush 0
    # Reg: [0,1,1,0]
    # G1: 0*1+1*1+1*0+0*1 = 1 -> 1
    # G2: 0*1+1*1+1*1+0*1 = 0 -> 0
    # Output: 10

    # Flush 0
    # Reg: [0,0,1,1]
    # G1: 0*1+0*1+1*0+1*1 = 1 -> 1
    # G2: 0*1+0*1+1*1+1*1 = 0 -> 0
    # Output: 10

    # Flush 0
    # Reg: [0,0,0,1]
    # G1: 0*1+0*1+0*0+1*1 = 1 -> 1
    # G2: 0*1+0*1+0*1+1*1 = 1 -> 1
    # Output: 11

    # Expected: '11111011101011'

except ValueError as e:
    print(f"Error: {e}")

# Example 2: Different message and generators
message_2 = '10'
generator_polynomials_2 = ['11', '10'] # G1 = 1+X^1, G2 = 1
# Number of memory elements = len(poly) - 1 = 2 - 1 = 1
# Initial state will be '0'

try:
    encoded_output_2 = convolutional_encode(message_2, generator_polynomials_2)
    print(f"\nMessage: {message_2}")
    print(f"Generator Polynomials: {generator_polynomials_2}")
    print(f"Encoded Output: {encoded_output_2}")
    # Expected output for message '10' and initial state '0' (G1=11, G2=10):
    # Input: 1
    # Reg: [1,0]
    # G1: 1*1+0*1 = 1 -> 1
    # G2: 1*1+0*0 = 1 -> 1
    # Output: 11

    # Input: 0
    # Reg: [0,1]
    # G1: 0*1+1*1 = 1 -> 1
    # G2: 0*1+1*0 = 0 -> 0
    # Output: 10

    # Flush 0
    # Reg: [0,0]
    # G1: 0*1+0*1 = 0 -> 0
    # G2: 0*1+0*0 = 0 -> 0
    # Output: 00

    # Expected: '111000'
except ValueError as e:
    print(f"Error: {e}")

# Example 3: Invalid input - non-binary message
message_3 = '1012'
generator_polynomials_3 = ['110', '101']
try:
    convolutional_encode(message_3, generator_polynomials_3)
except ValueError as e:
    print(f"\nError (invalid message_bits): {e}")

# Example 4: Invalid input - generator polynomial with invalid chars
message_4 = '101'
generator_polynomials_4 = ['110', '10X']
try:
    convolutional_encode(message_4, generator_polynomials_4)
except ValueError as e:
    print(f"\nError (invalid generator_polynomials): {e}")

# Example 5: Invalid input - generator polynomials with different lengths
message_5 = '101'
generator_polynomials_5 = ['110', '10']
try:
    convolutional_encode(message_5, generator_polynomials_5)
except ValueError as e:
    print(f"\nError (generator_polynomials different lengths): {e}")

# Example 6: Invalid input - initial_state wrong length
message_6 = '101'
generator_polynomials_6 = ['110', '101'] # 2 memory elements
initial_state_6 = '0'
try:
    convolutional_encode(message_6, generator_polynomials_6, initial_state_6)
except ValueError as e:
    print(f"\nError (initial_state wrong length): {e}")
