	import { NgModule }      from '@angular/core';
	import { BrowserModule } from '@angular/platform-browser';
	import { FormsModule }   from '@angular/forms';
	import { AppComponent }   from './app.component';
	import { LoginComponent } from './login.component';
	import { DocumentsComponent } from './documents.component';
	import { RouterModule }   from '@angular/router';
	import { HttpModule }    from '@angular/http';

	@NgModule({
	  imports:      [ BrowserModule, FormsModule,
    				HttpModule, RouterModule.forRoot([
	  {
	    path: 'login',
	    component: LoginComponent
	  },{
	  path: 'documents',
	  component: DocumentsComponent
	},{
  path: '',
  redirectTo: '/login',
  pathMatch: 'full'
}

	]) ],
	  declarations: [ AppComponent, LoginComponent, DocumentsComponent ],
	  bootstrap:    [ AppComponent ]
	})


	export class AppModule { }

