
package services;

import models.Admin;


public interface AdminService {
    public Admin logAdmin(String email, String password);
}