import { TestBed } from '@angular/core/testing';

import { CeplaService } from './cepla.service';

describe('CeplaService', () => {
  let service: CeplaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CeplaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
