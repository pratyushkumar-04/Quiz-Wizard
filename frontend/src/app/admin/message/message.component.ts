import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent {
  constructor(private http: HttpClient,private admservice :AdminService) {}
  txt:string='';

  callSecuredApi() {
    this.admservice.hellow();
  }
}
