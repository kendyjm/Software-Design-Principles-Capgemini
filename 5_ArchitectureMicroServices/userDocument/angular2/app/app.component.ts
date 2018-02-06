import { Component } from '@angular/core';
import { ProxyService } from './proxy.service';

@Component({
  selector: 'my-app',
  templateUrl: 'app/app.component.html',
  providers: [ProxyService]
})
export class AppComponent { 
 title = 'Your drive !';


 constructor(private proxyService: ProxyService) {
 	console.log("AppComponent construct " + this.proxyService);
  }
}

