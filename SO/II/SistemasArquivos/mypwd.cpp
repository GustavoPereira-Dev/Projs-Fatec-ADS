 #include <iostream>
 #include <unistd.h>
 
 int main() {
    char tmp[256];
    getcwd(tmp, 256);
	
    std::cout << "Directory: " << tmp << std::endl;
    return 0;
}
