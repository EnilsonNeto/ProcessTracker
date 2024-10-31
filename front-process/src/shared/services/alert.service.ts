import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  constructor() { }

  showAlert(icon: 'success' | 'error' | 'warning', text: string) {
    Swal.fire({
      icon: icon,
      title: '',
      text: text,
      position: 'bottom-end',
      toast: true,
      timer: 3000,
      showConfirmButton: false,
      timerProgressBar: true
    });
  }
}