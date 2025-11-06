import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: () => import('./landing/landing.module').then(m => m.LandingModule) },
  {path:'admin',loadChildren: () => import('./admin/admin.module').then(m=>m.AdminModule)},
    {path:'user',loadChildren: () => import('./user/user.module').then(m=>m.UserModule)},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
