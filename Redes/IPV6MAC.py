def ipv6_hex_to_decimal(ipv6_hex_str):
    """
    Converts an IPv6 hexadecimal string (e.g., "2001:0db8:85a3:0000:0000:8a2e:0370:7334" or "::1")
    to its large decimal integer representation.
    """
    # Handle compressed IPv6 format (::)
    if '::' in ipv6_hex_str:
        parts = ipv6_hex_str.split('::')
        left = parts[0].split(':') if parts[0] else []
        right = parts[1].split(':') if parts[1] else []
        missing_parts = 8 - (len(left) + len(right))
        expanded_parts = left + ['0000'] * missing_parts + right
    else:
        expanded_parts = ipv6_hex_str.split(':')

    if len(expanded_parts) != 8:
        raise ValueError(f"Invalid IPv6 address format: {ipv6_hex_str}")

    full_hex = ""
    for part in expanded_parts:
        if not all(c in '0123456789abcdefABCDEF' for c in part):
            raise ValueError(f"Invalid hexadecimal characters in IPv6 address: {part}")
        full_hex += part.zfill(4)

    try:
        return int(full_hex, 16)
    except ValueError as e:
        raise ValueError(f"Error converting IPv6 hex to decimal: {e}")

def ipv6_decimal_to_hex(ipv6_decimal_int):
    """
    Converts a large decimal integer to its canonical IPv6 hexadecimal string representation.
    """
    if not isinstance(ipv6_decimal_int, int) or not (0 <= ipv6_decimal_int <= 2**128 - 1):
        raise ValueError("Input must be an integer between 0 and 2^128 - 1 for IPv6.")

    # Convert to a 32-character hex string (128 bits = 32 hex chars)
    full_hex = f"{ipv6_decimal_int:032x}"

    # Format into 8 groups of 4 hex digits separated by colons
    formatted_parts = [full_hex[i:i+4] for i in range(0, 32, 4)]

    # Attempt to compress (::) for canonical form
    # This simple compression might not produce the 'most' canonical form for all cases,
    # but it handles common ones like '::1' and general compression.
    longest_zero_sequence = -1
    longest_zero_start = -1
    current_zero_sequence = 0
    current_zero_start = -1

    for i, part in enumerate(formatted_parts):
        if int(part, 16) == 0:
            if current_zero_sequence == 0:
                current_zero_start = i
            current_zero_sequence += 1
        else:
            if current_zero_sequence > longest_zero_sequence:
                longest_zero_sequence = current_zero_sequence
                longest_zero_start = current_zero_start
            current_zero_sequence = 0
            current_zero_start = -1

    if current_zero_sequence > longest_zero_sequence: # Check after loop for trailing zeros
        longest_zero_sequence = current_zero_sequence
        longest_zero_start = current_zero_start

    if longest_zero_sequence > 1: # Only compress if more than one '0000' segment
        compressed_parts = []
        i = 0
        while i < 8:
            if i == longest_zero_start:
                compressed_parts.append('') # Represents '::'
                i += longest_zero_sequence
            else:
                compressed_parts.append(formatted_parts[i].lstrip('0') or '0') # Remove leading zeros, keep '0' for '0000'
                i += 1
        # Special handling for '::' at start or end
        if longest_zero_start == 0 and longest_zero_sequence == 8: # All zeros
            return '::'
        elif longest_zero_start == 0:
            return '::' + ':'.join(compressed_parts[1:])
        elif longest_zero_start + longest_zero_sequence == 8:
            return ':'.join(compressed_parts[:-1]) + '::'
        else:
            # Join and replace double colon
            return ':'.join(compressed_parts).replace(':::', '::')
    else:
        return ':'.join(part.lstrip('0') or '0' for part in formatted_parts)

def mac_hex_to_decimal(mac_hex_str):
    """
    Converts a MAC hexadecimal string (e.g., "00:1A:2B:3C:4D:5E", "00-1A-2B-3C:4D-5E", or "aabb.ccdd.eeff")
    to its decimal integer representation.
    """
    # Remove common separators
    cleaned_hex = mac_hex_str.replace(':', '').replace('-', '').replace('.', '') # Added dot for cisco style
    if len(cleaned_hex) != 12: # A MAC address is 48 bits, so 12 hex chars
        raise ValueError("Invalid MAC address hex string length. Must be 12 hexadecimal characters.")
    try:
        return int(cleaned_hex, 16)
    except ValueError:
        raise ValueError("Invalid hexadecimal characters in MAC string.")

def mac_decimal_to_hex(mac_decimal_int):
    """
    Converts a decimal integer to its canonical MAC hexadecimal string representation
    (e.g., "00:1A:2B:3C:4D:5E").
    """
    if not isinstance(mac_decimal_int, int) or not (0 <= mac_decimal_int <= 2**48 - 1):
        raise ValueError("Input must be an integer between 0 and 2^48 - 1 for MAC address.")

    # Convert to a 12-character hex string (48 bits)
    hex_str = f"{mac_decimal_int:012x}"
    # Format into 6 groups of 2 hex digits separated by colons
    formatted_hex = ':'.join(hex_str[i:i+2].upper() for i in range(0, 12, 2)) # MACs are typically uppercase
    return formatted_hex

# --- Exemplos de Uso ---
print("--- Conversão IPv6 ---")
ipv6_hex_example_full = "2001:0db8:85a3:0000:0000:8a2e:0370:7334"
ipv6_hex_example_compressed = "2001:db8::1"
ipv6_hex_example_loopback = "::1"
ipv6_hex_example_all_zeros = "::" # Add all zeros example

try:
    ipv6_dec_full = ipv6_hex_to_decimal(ipv6_hex_example_full)
    print(f"IPv6 Hex (full): {ipv6_hex_example_full} -> Decimal: {ipv6_dec_full}")
    ipv6_hex_back_full = ipv6_decimal_to_hex(ipv6_dec_full)
    print(f"IPv6 Decimal: {ipv6_dec_full} -> Hex: {ipv6_hex_back_full}")
except ValueError as e:
    print(f"Erro IPv6 (full): {e}")

try:
    ipv6_dec_compressed = ipv6_hex_to_decimal(ipv6_hex_example_compressed)
    print(f"IPv6 Hex (compressed): {ipv6_hex_example_compressed} -> Decimal: {ipv6_dec_compressed}")
    ipv6_hex_back_compressed = ipv6_decimal_to_hex(ipv6_dec_compressed)
    print(f"IPv6 Decimal: {ipv6_dec_compressed} -> Hex: {ipv6_hex_back_compressed}")
except ValueError as e:
    print(f"Erro IPv6 (compressed): {e}")

try:
    ipv6_dec_loopback = ipv6_hex_to_decimal(ipv6_hex_example_loopback)
    print(f"IPv6 Hex (loopback): {ipv6_hex_example_loopback} -> Decimal: {ipv6_dec_loopback}")
    ipv6_hex_back_loopback = ipv6_decimal_to_hex(ipv6_dec_loopback)
    print(f"IPv6 Decimal: {ipv6_dec_loopback} -> Hex: {ipv6_hex_back_loopback}")
except ValueError as e:
    print(f"Erro IPv6 (loopback): {e}")

try:
    ipv6_dec_all_zeros = ipv6_hex_to_decimal(ipv6_hex_example_all_zeros)
    print(f"IPv6 Hex (all zeros): {ipv6_hex_example_all_zeros} -> Decimal: {ipv6_dec_all_zeros}")
    ipv6_hex_back_all_zeros = ipv6_decimal_to_hex(ipv6_dec_all_zeros)
    print(f"IPv6 Decimal: {ipv6_dec_all_zeros} -> Hex: {ipv6_hex_back_all_zeros}")
except ValueError as e:
    print(f"Erro IPv6 (all zeros): {e}")

print("\n--- Conversão MAC ---")
mac_hex_example = "00:1A:2B:3C:4D:5E"
mac_hex_example_dash = "AB-CD-EF-01-23-45"
mac_hex_example_cisco = "aabb.ccdd.eeff" # Added cisco style

try:
    mac_dec = mac_hex_to_decimal(mac_hex_example)
    print(f"MAC Hex (colon): {mac_hex_example} -> Decimal: {mac_dec}")
    mac_hex_back = mac_decimal_to_hex(mac_dec)
    print(f"MAC Decimal: {mac_dec} -> Hex: {mac_hex_back}")
except ValueError as e:
    print(f"Erro MAC (colon): {e}")

try:
    mac_dec_dash = mac_hex_to_decimal(mac_hex_example_dash)
    print(f"MAC Hex (dash): {mac_hex_example_dash} -> Decimal: {mac_dec_dash}")
    mac_hex_back_dash = mac_decimal_to_hex(mac_dec_dash)
    print(f"MAC Decimal: {mac_dec_dash} -> Hex: {mac_hex_back_dash}")
except ValueError as e:
    print(f"Erro MAC (dash): {e}")

try:
    mac_dec_cisco = mac_hex_to_decimal(mac_hex_example_cisco)
    print(f"MAC Hex (Cisco): {mac_hex_example_cisco} -> Decimal: {mac_dec_cisco}")
    mac_hex_back_cisco = mac_decimal_to_hex(mac_dec_cisco)
    print(f"MAC Decimal: {mac_dec_cisco} -> Hex: {mac_hex_back_cisco}")
except ValueError as e:
    print(f"Erro MAC (Cisco): {e}")