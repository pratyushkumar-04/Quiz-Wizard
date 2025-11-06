import { Component } from '@angular/core';
import { UserserviceService } from '../shared/userservice.service';
import { ActivatedRoute, Route } from '@angular/router';

@Component({
  selector: 'app-analysis',
  templateUrl: './analysis.component.html',
  styleUrls: ['./analysis.component.css']
})
export class AnalysisComponent {

   resultId!: number;
  result: any;

  constructor(private route: ActivatedRoute, private uservice: UserserviceService) {}

  ngOnInit() {
    this.resultId = +this.route.snapshot.paramMap.get('resultId')!;
    this.uservice.getResultById(this.resultId).subscribe({
      next: (data) => {
        this.result = data;
        console.log('Loaded result', data);
      },
      error: (err) => {
        console.error('Failed to fetch result', err);
      }
    });
  }
}
