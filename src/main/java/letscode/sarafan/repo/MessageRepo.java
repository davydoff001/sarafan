/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letscode.sarafan.repo;

import letscode.sarafan.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alexandr
 */
public interface MessageRepo extends JpaRepository<Message, Long>{
    
}
