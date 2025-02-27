 #include <iostream>
 #include <filesystem>
 
 namespace fs = std::filesystem;
 
 void listar(std::string path)
 {
    for (const auto & entry : fs::directory_iterator(path))
    {
        std::cout << entry.path() << std::endl;
        if( is_directory( entry.path() ) )
        {
            listar(  entry.path()  );
        }
    }
}

 int main()
 {
    listar("/home");
 }