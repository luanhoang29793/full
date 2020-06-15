import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router
import { IndexComponent } from './trangchu/index/index.component';
import { AdminComponent } from './admin/admin/admin.component';
import { CreateSComponent } from './admin/create-s/create-s.component';
import { CreatelistAComponent } from './admin/createlist-a/createlist-a.component';
import { CreatelistCComponent } from './admin/createlist-c/createlist-c.component';
import { ListSComponent } from './admin/list-s/list-s.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { UploadImageComponent } from './admin/upload-image/upload-image.component';


const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' },
 
 
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'user',
    loadChildren: () => import('./trangchu/user.module').then(m => m.UserModule)
  }
 




]; 


@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true}),BrowserModule, FormsModule],
  exports: [RouterModule]

})
export class AppRoutingModule { }