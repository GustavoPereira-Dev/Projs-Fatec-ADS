#include <stdio.h>
#include <curl/curl.h>

int main(){ 
   CURL *curl;        
   FILE *fp; 
   CURLcode res; 
   char *url = "url"; 

   char outfilename[FILENAME_MAX] = "/tmp/output_image.iso"; 
   curl = curl_easy_init();
   if ( curl ) {    
      fp = fopen( outfilename, "wb"); 
      curl_easy_setopt(curl, CURLOPT_URL, url); 
      curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, NULL); 
      curl_easy_setopt(curl, CURLOPT_WRITEDATA, fp); 
      res = curl_easy_perform(curl); 
	  curl_easy_cleanup(curl);
	  fclose(fp);
   }
   return 0;
}
   