package Services;

import Dtos.RemoteControllerDto;
import Exceptions.RecordNotFoundException;
import Models.RemoteController;
import Repositories.RemoteControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService  {

    @Autowired
    private RemoteControllerRepository remoteControllerRepository;

    public List<RemoteControllerDto> getAllRemoteControllers() {
        List<RemoteControllerDto> dtos = new ArrayList<>();
        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        for (RemoteController rc : remoteControllers) {
            dtos.add(transferToDto(rc));
        }
        return dtos;
    }

    public RemoteControllerDto getRemoteController(long id) {
        Optional<RemoteController> remoteController = remoteControllerRepository.findById(id);
        if(remoteController.isPresent()) {
            return transferToDto(remoteController.get());
        } else {
            throw new RecordNotFoundException("No remotecontroller found");
        }
    }

    public RemoteControllerDto addRemoteController(RemoteControllerDto remoteControllerDto) {
        RemoteController rc =  transferToRemoteController(remoteControllerDto);
        remoteControllerRepository.save(rc);
        return remoteControllerDto;
    }

    public void deleteRemoteController(Long id) {
        remoteControllerRepository.deleteById(id);
    }

    public void updateRemoteController(Long id, RemoteControllerDto remoteControllerDto) {
        if(!remoteControllerRepository.existsById(id)) {
            throw new RecordNotFoundException("No remotecontroller found");
        }
        RemoteController storedRemoteController = remoteControllerRepository.findById(id).orElse(null);
        storedRemoteController.setId(remoteControllerDto.getId());
        storedRemoteController.setCompatibleWith(remoteControllerDto.getCompatibleWith());
        storedRemoteController.setBatteryType(remoteControllerDto.getBatteryType());
        storedRemoteController.setName(remoteControllerDto.getName());
        storedRemoteController.setPrice(remoteControllerDto.getPrice());
        storedRemoteController.setBrand(remoteControllerDto.getBrand());
        storedRemoteController.setOriginalStock(remoteControllerDto.getOriginalStock());
        remoteControllerRepository.save(storedRemoteController);
    }

    public RemoteControllerDto transferToDto(RemoteController remoteController){
        var dto = new RemoteControllerDto();

        dto.id = remoteController.getId();
        dto.compatibleWith = remoteController.getCompatibleWith();
        dto.batteryType = remoteController.getBatteryType();
        dto.name = remoteController.getName();
        dto.brand = remoteController.getBrand();
        dto.price = remoteController.getPrice();
        dto.originalStock = remoteController.getOriginalStock();

        return dto;
    }

    public RemoteController transferToRemoteController(RemoteControllerDto dto){
        var remoteController = new RemoteController();

        remoteController.setId(dto.getId());
        remoteController.setCompatibleWith(dto.getCompatibleWith());
        remoteController.setBatteryType(dto.getBatteryType());
        remoteController.setName(dto.getName());
        remoteController.setBrand(dto.getBrand());
        remoteController.setPrice(dto.getPrice());
        remoteController.setOriginalStock(dto.getOriginalStock());

        return remoteController;
    }

}
