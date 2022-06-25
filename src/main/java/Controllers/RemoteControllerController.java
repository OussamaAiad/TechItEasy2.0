package Controllers;

import Dtos.RemoteControllerDto;
import Services.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    @Autowired
    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }


    @GetMapping("/remotecontrollers")
    public List<RemoteControllerDto> getAllRemotecontrollers() {

        List<RemoteControllerDto> dtos = remoteControllerService.getAllRemoteControllers();

        return dtos;
    }

    @GetMapping("/remotecontrollers/{id}")
    public RemoteControllerDto getRemotecontroller(@PathVariable("id") Long id) {

        RemoteControllerDto dto = remoteControllerService.getRemoteController(id);

        return dto;
    }

    @PostMapping("/remotecontrollers")
    public RemoteControllerDto addRemoteController(@RequestBody RemoteControllerDto dto) {
        RemoteControllerDto dto1 = remoteControllerService.addRemoteController(dto);
        return dto1;
    }

    @DeleteMapping("/remotecontrollers/{id}")
    public void deleteRemoteController(@PathVariable("id") Long id) {
        remoteControllerService.deleteRemoteController(id);
    }

    @PutMapping("/remotecontrollers/{id}")
    public RemoteControllerDto updateTelevision(@PathVariable("id") Long id, @RequestBody RemoteControllerDto dto) {
        remoteControllerService.updateRemoteController(id, dto);
        return dto;
    }

}