#!/usr/bin/python3
# gerarsha1.py

import hashlib;

umtexto = '123456';
hash_object = hashlib.sha1(umtexto.encode());
bHash = hash_object.hexdigest(); 9. print(bHash);
