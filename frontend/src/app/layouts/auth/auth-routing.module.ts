import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthComponent } from './auth.component';
import { PageNotFoundComponent } from 'src/app/page-not-found/page-not-found.component';
import { AuthFormComponent } from './components/auth-form/auth-form.component';
const routes: Routes = [

  {
    path:'auth',
    component: AuthComponent,
    children:[
      {path:'login', component: AuthFormComponent},
     // {path:'register', component: RegisterComponent},



      { path: '404',  component: PageNotFoundComponent },
  { path: '**',  redirectTo: '/404' },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule { }
