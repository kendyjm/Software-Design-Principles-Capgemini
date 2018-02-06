import { Injectable } from '@angular/core';

import { Membre } from './membre';
import { Document } from './document';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';


@Injectable()
export class ProxyService {
	private proxyServer = 'http://localhost:8081'
	private authenticateUrl =  '/members-service/authenticate'; 
	private documentsUrl = '/documents-service/Documents/owner/';  

	membre:Membre;


  constructor(private http: Http) { 
    
  }


  authenticate(membre,handleError): Promise<Membre> {
  	return this.http.post(this.proxyServer + this.authenticateUrl,membre).toPromise().then(response => this.initMembre(response)).catch(handleError);
}

getDocuments(): Promise<Document[]> {
	// A compléter

}
 initMembre(response): Membre {
 	this.membre = response.json();
 	return this.membre;
 }
 initDocuments(response): Document[] {

 	var documents  = response.json();
 	return documents;
 }
}
