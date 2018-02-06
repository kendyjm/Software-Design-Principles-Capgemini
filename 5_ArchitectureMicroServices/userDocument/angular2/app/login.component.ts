import { Component } from '@angular/core';
import { Membre } from './membre';
import { ProxyService } from './proxy.service';
import { Router } from '@angular/router';

@Component({
  selector: 'my-login',
  templateUrl: 'app/login.component.html'
})
export class LoginComponent { 
membre:Membre = new Membre();
message="";

constructor(
	private router: Router,
	private proxyService: ProxyService) { }

onEnter(): void {
    this.proxyService.authenticate(this.membre,function() {
    	this.message = "Try again";
    }).then(membre => this.showDocuments(membre));
  }


 showDocuments(membre): void {
    this.membre = membre;
 	this.router.navigate(['documents']);
 }
}

