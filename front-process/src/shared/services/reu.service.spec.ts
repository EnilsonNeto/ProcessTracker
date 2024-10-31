import { TestBed } from '@angular/core/testing';

import { ReuService } from './reu.service';

describe('ReuService', () => {
  let service: ReuService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReuService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
