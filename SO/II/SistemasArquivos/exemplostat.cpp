#include<iostream>
#include<sys/stat.h>

using namespace std;

int main()
{
   std::string path = "/tmp";
   struct stat var;
   const char* chr = path.c_str();
   int ret= stat( chr , &var );
   if( ret < 0 )
   {
   
      cout << "A chamada do sistema ‘stat’ foi encerrada com um código de erro: " << ret << endl;
   }
  
   else
   {
     cout << "ID do device: " << var.st_dev << endl;
     cout << "Número Inode: " << var.st_ino << endl;
     cout << "Mode: " << var.st_mode << endl;
     cout << "UID: " << var.st_uid << endl;
     cout << "GID: " << var.st_gid << endl;
     cout << "Size: " << var.st_size << endl;
   }
   
   struct stat
   {
      dev_t   st_dev;             /* ID do dispositivo que contém o arquivo */
      ino_t   st_ino;             /* número inode */
      modo_t  st_mode;            /* proteção */
      nlink_t   st_nlink;         /* número de links físicos */
      uid_t   st_uid;             /* ID de usuário do proprietário */
      gid_t   st_gid;             /* ID do grupo do proprietário */
      off_t   st_size;            /* tamanho total, em bytes */
      blksize_t   st_blksize;     /* block size for filesystem I/O */
      blkcnt_t    st_blocks;      /* número de blocos de 512 Bytes alocados */
   };
}

void exemplosstat(){
   main();
}