import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegestComponent } from './regest/regest.component';
import { SingINComponent } from './sing-in/sing-in.component';

export const routes: Routes = [
  { path: 'register', component: RegestComponent },
  { path: 'login', component: SingINComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Перенаправлення на логін за замовчуванням
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
