def hamming_distance(binary_string1, binary_string2):
    """
    Calculates the Hamming distance between two binary strings.

    The Hamming distance between two strings of equal length is the number
    of positions at which the corresponding symbols are different.

    Args:
        binary_string1 (str): The first binary string.
        binary_string2 (str): The second binary string.

    Returns:
        int: The Hamming distance between the two strings.

    Raises:
        ValueError: If the strings are not binary or do not have the same length.
    """
    # 1. Input Validation
    if not all(c in '01' for c in binary_string1) or not all(c in '01' for c in binary_string2):
        raise ValueError("Both input strings must be binary (contain only '0's and '1's).")

    if len(binary_string1) != len(binary_string2):
        raise ValueError("Input strings must have the same length.")

    # 2. Hamming Distance Calculation
    distance = 0
    for i in range(len(binary_string1)):
        if binary_string1[i] != binary_string2[i]:
            distance += 1
            
    return distance

# --- Example Usage ---
print("### Hamming Distance Calculation ###")

# Valid examples
example1_str1 = "1011001"
example1_str2 = "1010001"
try:
    dist1 = hamming_distance(example1_str1, example1_str2)
    print(f"\nStrings: '{example1_str1}', '{example1_str2}'")
    print(f"Hamming Distance: {dist1}") # Expected: 1
except ValueError as e:
    print(f"Error for '{example1_str1}', '{example1_str2}': {e}")

example2_str1 = "11110000"
example2_str2 = "00001111"
try:
    dist2 = hamming_distance(example2_str1, example2_str2)
    print(f"\nStrings: '{example2_str1}', '{example2_str2}'")
    print(f"Hamming Distance: {dist2}") # Expected: 8
except ValueError as e:
    print(f"Error for '{example2_str1}', '{example2_str2}': {e}")

example3_str1 = "00000"
example3_str2 = "00000"
try:
    dist3 = hamming_distance(example3_str1, example3_str2)
    print(f"\nStrings: '{example3_str1}', '{example3_str2}'")
    print(f"Hamming Distance: {dist3}") # Expected: 0
except ValueError as e:
    print(f"Error for '{example3_str1}', '{example3_str2}': {e}")

# Invalid examples (different lengths)
example_invalid_len_str1 = "101"
example_invalid_len_str2 = "1010"
try:
    dist_invalid_len = hamming_distance(example_invalid_len_str1, example_invalid_len_str2)
    print(f"\nStrings: '{example_invalid_len_str1}', '{example_invalid_len_str2}'")
    print(f"Hamming Distance: {dist_invalid_len}")
except ValueError as e:
    print(f"Error for '{example_invalid_len_str1}', '{example_invalid_len_str2}': {e}")

# Invalid examples (non-binary characters)
example_invalid_char_str1 = "10120"
example_invalid_char_str2 = "10110"
try:
    dist_invalid_char = hamming_distance(example_invalid_char_str1, example_invalid_char_str2)
    print(f"\nStrings: '{example_invalid_char_str1}', '{example_invalid_char_str2}'")
    print(f"Hamming Distance: {dist_invalid_char}")
except ValueError as e:
    print(f"Error for '{example_invalid_char_str1}', '{example_invalid_char_str2}': {e}")

example_invalid_char2_str1 = "abc"
example_invalid_char2_str2 = "def"
try:
    dist_invalid_char2 = hamming_distance(example_invalid_char2_str1, example_invalid_char2_str2)
    print(f"\nStrings: '{example_invalid_char2_str1}', '{example_invalid_char2_str2}'")
    print(f"Hamming Distance: {dist_invalid_char2}")
except ValueError as e:
    print(f"Error for '{example_invalid_char2_str1}', '{example_invalid_char2_str2}': {e}")
