#include <stdio.h>
#include <curl/curl.h>
#include <curl/curl.h>
#include <iostream>
#include <cstdio>
#include "utils.h"

void fazerDownloadArquivo(const std::string& url, const std::string& destino) {
    CURL* curl = curl_easy_init();
    if (!curl) {
        std::cerr << "Erro ao iniciar cURL" << std::endl;
        return;
    }

    FILE* fp = fopen(destino.c_str(), "wb");
    if (!fp) {
        std::cerr << "Erro ao abrir arquivo destino: " << destino << std::endl;
        curl_easy_cleanup(curl);
        return;
    }

    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, nullptr);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, fp);

    CURLcode res = curl_easy_perform(curl);
    if (res != CURLE_OK)
        std::cerr << "Erro ao fazer download: " << curl_easy_strerror(res) << std::endl;

    fclose(fp);
    curl_easy_cleanup(curl);
}

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
   