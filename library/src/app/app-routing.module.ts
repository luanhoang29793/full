import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';



const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' },
 
 
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'user',
    loadChildren: () => import('./trangchu/user.module').then(m => m.UserModule)
  },

 




]; 


@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true}),BrowserModule, FormsModule],
  exports: [RouterModule]

})
export class AppRoutingModule { }