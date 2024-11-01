import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ProcessosComponent } from './components/processos/processos.component';
@NgModule({
  imports: [
    RouterModule.forRoot([ 
      { path: '', component: ProcessosComponent },
    ])
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }