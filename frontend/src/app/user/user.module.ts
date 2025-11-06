import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { QuizcategoriesComponent } from './quizcategories/quizcategories.component';
import { QuizstartComponent } from './quizstart/quizstart.component';
import { LayoutComponent } from './layout/layout.component';
import { SharedModule } from './shared/shared.module';
import { ResultComponent } from './result/result.component';
import { AnalysisComponent } from './analysis/analysis.component';
import { LeaderboardComponent } from './leaderboard/leaderboard.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { FormsModule } from '@angular/forms';
import { ChangePasswordComponent } from './change-password/change-password.component';


@NgModule({
  declarations: [
    DashboardComponent,
    QuizcategoriesComponent,
    QuizstartComponent,
    LayoutComponent,
    ResultComponent,
    AnalysisComponent,
    LeaderboardComponent,
    UpdateProfileComponent,
    ChangePasswordComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedModule,
    FormsModule
  ]
})
export class UserModule { }
