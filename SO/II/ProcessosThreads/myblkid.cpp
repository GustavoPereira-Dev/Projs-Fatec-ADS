#include <err.h>
#include <blkid/blkid.h>
#include <string> 

int main (int argc, char *argv[]) {
   blkid_probe pr;
   const char *uuid;
   std::string partition = "/dev/sda1";
   
   pr = blkid_new_probe_from_filename( partition.c_str() );
   if (!pr) {
      err(2, "Failed to open %s", partition);
   }
   
   blkid_do_probe(pr);
   blkid_probe_lookup_value(pr, "UUID", &uuid, NULL);
   
   printf("UUID=%s\n", uuid);
   
   blkid_free_probe(pr);
   
   return 0;
}