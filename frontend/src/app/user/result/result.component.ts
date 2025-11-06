import { Component } from '@angular/core';
import { UserserviceService } from '../shared/userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent {

  results:any[]=[];
  constructor(private uservice: UserserviceService,private router:Router){}

  ngOnInit()
  {
    this.uservice.getPastResults().subscribe({
      next: (data) => {
        this.results = data;
      },
      error: (err) => {
        console.error('Failed to fetch results', err);
      }
    });
  }
  goToAnalysis(resultId: number) {
  this.router.navigate(['/user/analysis', resultId]);
}

}
